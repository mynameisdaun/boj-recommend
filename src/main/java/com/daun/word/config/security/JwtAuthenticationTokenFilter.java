package com.daun.word.config.security;

import com.daun.word.global.GlobalId;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import com.daun.word.domain.member.domain.vo.SocialType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends GenericFilterBean {

    private static final Pattern BEARER = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);

    private final String headerKey;

    private final Jwt jwt;

    //TODO: filter 수정사항!
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String authorizationToken = obtainAuthorizationToken(request);
            if (authorizationToken != null) {
                try {
                    Jwt.Claims claims = verify(authorizationToken);
                    if (canRefresh(claims, 6000 * 10)) {
                        String refreshedToken = jwt.refreshToken(authorizationToken);
                        response.setHeader(headerKey, refreshedToken);
                    }
                    UUID userGlobalId = claims.memberGlobalId;
                    Name name = claims.name;
                    Email email = claims.email;
                    SocialType socialType = claims.socialType;

                    List<GrantedAuthority> authorities = obtainAuthorities(claims);

                    if (nonNull(userGlobalId) && nonNull(name) && nonNull(email) && authorities.size() > 0) {
                        JwtAuthenticationToken authentication =
                                new JwtAuthenticationToken(new JwtAuthentication(userGlobalId, email, name, socialType), null, socialType, authorities);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (Exception e) {
                    log.warn("Jwt processing failed: {}", e.getMessage());
                }
            }
        } else {
            log.debug("SecurityContextHolder not populated with security token, as it already contained: '{}'",
                    SecurityContextHolder.getContext().getAuthentication());
        }

        chain.doFilter(request, response);
    }

    private boolean canRefresh(Jwt.Claims claims, long refreshRangeMillis) {
        long exp = claims.exp();
        if (exp > 0) {
            long remain = exp - System.currentTimeMillis();
            return remain < refreshRangeMillis;
        }
        return false;
    }

    private List<GrantedAuthority> obtainAuthorities(Jwt.Claims claims) {
        String[] roles = claims.roles;
        return roles == null || roles.length == 0 ?
                Collections.emptyList() :
                Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(toList());
    }

    private String obtainAuthorizationToken(HttpServletRequest request) {
        String token = request.getHeader(headerKey);
        if (token != null) {
            if (log.isDebugEnabled())
                log.debug("Jwt authorization api detected: {}", token);
            try {
                token = URLDecoder.decode(token, "UTF-8");
                String[] parts = token.split(" ");
                if (parts.length == 2) {
                    String scheme = parts[0];
                    String credentials = parts[1];
                    return BEARER.matcher(scheme).matches() ? credentials : null;
                }
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    private Jwt.Claims verify(String token) {
        return jwt.verify(token);
    }
}
