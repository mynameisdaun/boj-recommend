package com.daun.word.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;


public class JwtUtils {

    @Value("${OAuth.jwt.access.key}")
    private String accessKey;
    @Value("${OAuth.jwt.refresh.key}")
    private String refreshKey;
    @Value("${OAuth.jwt.duration.access}")
    private Long accessExpiresIn;
    @Value("${OAuth.jwt.duration.refresh}")
    private Long refreshExpiresIn;

    //TODO: AccessToken 정책 정리
    public String accessToken(int memberId) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("word")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + getAccessExpiresIn()))
                .claim("memberId", memberId)
                .signWith(SignatureAlgorithm.HS256, accessKey)
                .compact();
    }

    public String refreshToken(int memberId) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("word")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + getRefreshExpiresIn()))
                .claim("memberId", memberId)
                .signWith(SignatureAlgorithm.HS256, refreshKey)
                .compact();
    }

    public Claims verifyAccess(String authorizationHeader) {
        validationAuthorizationHeader(authorizationHeader); // (1)
        String token = extractToken(authorizationHeader); // (2)
        return Jwts.parser()
                .setSigningKey(accessKey) // (3)
                .parseClaimsJws(token) // (4)
                .getBody();
    }

    public Claims verifyRefresh(String authorizationHeader) {
        validationAuthorizationHeader(authorizationHeader); // (1)
        String token = extractToken(authorizationHeader); // (2)
        return Jwts.parser()
                .setSigningKey(refreshKey) // (3)
                .parseClaimsJws(token) // (4)
                .getBody();
    }

    public Long getAccessExpiresIn() {
        return accessExpiresIn;
    }

    public Long getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    private void validationAuthorizationHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new IllegalArgumentException();
        }
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }
}
