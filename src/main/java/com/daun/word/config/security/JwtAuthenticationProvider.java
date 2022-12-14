package com.daun.word.config.security;

import com.daun.word.auth.dto.AuthResponse;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final MemberService memberService;

    private final Jwt jwt;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        Member member = memberService.login(authenticationToken.toAuthRequest());
        JwtAuthenticationToken authenticated = new JwtAuthenticationToken(
                new JwtAuthentication(
                        member.getId(),
                        member.getEmail()
                ),
                null,
                createAuthorityList(member.getRole().value()));
        authenticated.setDetails(new AuthResponse(member, member.accessToken(jwt)));
        return authenticated;
    }
}
