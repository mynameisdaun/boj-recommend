package com.daun.word.config.security;

import com.daun.word.auth.dto.AuthenticationRequest;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Password;
import com.daun.word.member.domain.vo.SocialType;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@ToString
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    private Password credentials;

    private SocialType socialType;

    public JwtAuthenticationToken(Email principal, Password credentials, SocialType socialType) {
        super(null);
        super.setAuthenticated(false);
        this.principal = principal;
        this.credentials = credentials;
        this.socialType = socialType;
    }

    public JwtAuthenticationToken(Object principal, Password credentials, SocialType socialType, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);
        this.principal = principal;
        this.credentials = credentials;
        this.socialType = socialType;
    }

    public AuthenticationRequest authenticationRequest() {
        return new AuthenticationRequest(((Email) this.principal).getValue(), this.credentials.getValue(), this.socialType.name());
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

    public SocialType getSocialType() {
        return socialType;
    }
}
