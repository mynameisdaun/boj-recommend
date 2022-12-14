package com.daun.word.config.service;

import com.daun.word.config.security.Jwt;
import com.daun.word.config.security.JwtTokenConfigure;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfigure {
    @Bean
    public Jwt jwt(JwtTokenConfigure configure) {
        return new Jwt(configure.getIssuer(), configure.getSecret(), configure.getExpirySeconds());
    }
}
