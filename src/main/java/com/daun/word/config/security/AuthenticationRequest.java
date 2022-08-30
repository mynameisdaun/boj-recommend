package com.daun.word.config.security;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String principal;

    private String credentials;

    protected AuthenticationRequest() {}

    public AuthenticationRequest(String principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }

}
