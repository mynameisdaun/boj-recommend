package com.daun.word.config.security;

import com.daun.word.auth.dto.AuthenticationRequest;
import com.daun.word.commons.Id;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import com.daun.word.member.domain.vo.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final Jwt jwt;

    //private final MemberService memberService;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        return processMemberAuthentication(authenticationToken.authenticationRequest());
     }

    private Authentication processMemberAuthentication(AuthenticationRequest request) {
        try {
            //Member member = memberService.findByEmail(new Email(request.getPrincipal()));
            JwtAuthenticationToken authenticated =
                    // 응답용 Authentication 인스턴스를 생성한다.
                    // JwtAuthenticationToken.principal 부분에는 JwtAuthentication 인스턴스가 set 된다.
                    // 로그인 완료 전 JwtAuthenticationToken.principal 부분은 Email 인스턴스가 set 되어 있었다.
                    new JwtAuthenticationToken(
                            new JwtAuthentication(Id.of(Member.class,1), new Nickname("hi"), new Email("hi")), null, createAuthorityList(Role.USER.name()));
            // JWT 값을 생성한다.
            // 권한은 ROLE_USER 를 부여한다.
            //String apiToken = member.newToken(jwt, new String[]{Role.USER.name()});
            authenticated.setDetails(new AuthenticationResult("apiToken", null));
            return authenticated;
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (DataAccessException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }
}
