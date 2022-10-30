package com.daun.word.config.security;

import com.daun.word.global.vo.Name;
import com.daun.word.global.auth.dto.AuthenticationRequest;
import com.daun.word.global.auth.dto.AuthenticationResponse;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Role;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.member.service.MemberService;
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

    private final MemberService memberService;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    //TODO: 소셜타입에 맞게 로그인 방법 달라져야 된다.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        return processMemberAuthentication(authenticationToken.authenticationRequest());
    }
   /* TODO: 불필요한 코드들이 많아보인다.
    * */
    private Authentication processMemberAuthentication(AuthenticationRequest request) {
        try {
            //AuthenticationResponse response = memberService.login(request);
            AuthenticationResponse response = null;
            JwtAuthenticationToken authenticated =
                    // 응답용 Authentication 인스턴스를 생성한다.
                    // JwtAuthenticationToken.principal 부분에는 JwtAuthentication 인스턴스가 set 된다.
                    // 로그인 완료 전 JwtAuthenticationToken.principal 부분은 Email 인스턴스가 set 되어 있었다.
                    new JwtAuthenticationToken(
                            new JwtAuthentication(
                                    response.getId(),
                                    new Email(response.getEmail()),
                                    new Name(response.getNickname()),
                                    SocialType.valueOf(response.getSocialType())
                            ),
                            null,
                            SocialType.valueOf(response.getSocialType()),
                            createAuthorityList(Role.USER.name()));
            authenticated.setDetails(response);
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
