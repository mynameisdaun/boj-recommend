package com.daun.word.global.utils;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
public class JwtUtils {

    private final String accessKey;
    private final String refreshKey;
    private final Long accessExpiresIn;
    private final Long refreshExpiresIn;
    private Date date;

    //TODO: AccessToken 정책 정리
    public String accessToken(String email) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("word")
                .setIssuedAt(this.date)
                .setExpiration(new Date(this.date.getTime() + getAccessExpiresIn()))
                .claim("email", email)
                .signWith(SignatureAlgorithm.HS256, accessKey)
                .compact();
    }

    public String refreshToken(String email) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("word")
                .setIssuedAt(this.date)
                .setExpiration(new Date(this.date.getTime() + getRefreshExpiresIn()))
                .claim("email", email)
                .signWith(SignatureAlgorithm.HS256, refreshKey)
                .compact();
    }

//    public Claims verifyAccess(String authorizationHeader) {
//        validationAuthorizationHeader(authorizationHeader); // (1)
//        String token = extractToken(authorizationHeader); // (2)
//        return Jwts.parser()
//                .setSigningKey(accessKey) // (3)
//                .parseClaimsJws(token) // (4)
//                .getBody();
//    }
//
//    public Claims verifyRefresh(String authorizationHeader) {
//        validationAuthorizationHeader(authorizationHeader); // (1)
//        String token = extractToken(authorizationHeader); // (2)
//        return Jwts.parser()
//                .setSigningKey(refreshKey) // (3)
//                .parseClaimsJws(token) // (4)
//                .getBody();
//    }

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
