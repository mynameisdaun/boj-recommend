package com.daun.word.config.security;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Role;
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

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends GenericFilterBean {

    private final String headerKey;
    private final String tokenType;

    private final Jwt jwt;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String authorizationToken = parseAuthToken(request);
            log.info("authToken: {}", authorizationToken);
            if (authorizationToken != null) {
                try {
                    Jwt.Claims claims = verify(authorizationToken);
                    log.info("claims: {}", claims);
                    if (canRefresh(claims, 6000 * 10)) {
                        String refreshedToken = jwt.refreshToken(authorizationToken);
                        response.setHeader(headerKey, refreshedToken);
                    }
                    UUID memberId = claims.memberId;
                    Email email = claims.email;
                    List<GrantedAuthority> authorities = obtainAuthorities(claims);
                    if (nonNull(memberId) && nonNull(email) && authorities.size() > 0) {
                        log.info("email: {}, id: {}", email, memberId);
                        JwtAuthenticationToken authentication =
                                new JwtAuthenticationToken(new JwtAuthentication(memberId, email), null, authorities);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("Jwt processing failed: {}", e.getMessage());
                }
            }
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
        Role role = claims.role;
        return role == null ? Collections.emptyList()
                : Arrays.asList(role).stream().map(r -> new SimpleGrantedAuthority(r.value())).collect(toList());
    }

    private String parseAuthToken(HttpServletRequest request) {
        String token = request.getHeader(headerKey);
        log.info("token: {}", token);
        if (token != null) {
            try {
                token = URLDecoder.decode(token, "UTF-8");
                String[] parts = token.split(" ");
                if (parts.length == 2) {
                    String scheme = parts[0];
                    String authToken = parts[1];
                    return scheme.equals(tokenType) ? authToken : null;
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
