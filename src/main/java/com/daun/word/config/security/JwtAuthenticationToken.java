package com.daun.word.config.security;

import com.daun.word.auth.dto.AuthRequest;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Password;
import lombok.ToString;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@ToString
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    private Password credentials;

    public JwtAuthenticationToken(Email principal, Password credentials) {
        super(null);
        super.setAuthenticated(false);
        this.principal = principal;
        this.credentials = credentials;
    }

    public JwtAuthenticationToken(Object principal, Password credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);
        this.principal = principal;
        this.credentials = credentials;
    }

    public AuthRequest toAuthRequest() {
        return new AuthRequest(((Email) this.principal).getValue(), this.credentials.getValue());
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
}
