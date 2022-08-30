package com.daun.word.config.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtTokenConfigure {
    private String header;
    private String issuer;
    private String secret;
    private int expirySeconds;
}
