package com.daun.word.config.security;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.daun.word.commons.Id;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import lombok.Getter;

import java.util.Date;

@Getter
public final class Jwt {

    private final String issuer;

    private final String secret;

    private final int expirySeconds;

    private final Algorithm algorithm;

    private final JWTVerifier jwtVerifier;

    public Jwt(String issuer, String secret, int expirySeconds) {
        this.issuer = issuer;
        this.secret = secret;
        this.expirySeconds = expirySeconds;
        this.algorithm = Algorithm.HMAC512(secret);
        this.jwtVerifier = com.auth0.jwt.JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }

    public String newToken(Claims claims) {
        Date now = new Date();
        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();
        builder.withIssuer(issuer);
        builder.withIssuedAt(now);
        if (expirySeconds > 0) {
            builder.withExpiresAt(new Date(now.getTime() + expirySeconds * 1_000L));
        }
        builder.withClaim("memberId", claims.memberId.getValue());
        builder.withClaim("nickname", claims.nickname.getValue());
        builder.withClaim("email", claims.email.getValue());
        builder.withArrayClaim("roles", claims.roles);
        return builder.sign(algorithm);
    }

    //TODO: 이름이 refresh가 아니고,
    public String refreshToken(String token) throws JWTVerificationException {
        Claims claims = verify(token);
        claims.eraseIat();
        claims.eraseExp();
        return newToken(claims);
    }

    public Claims verify(String token) throws JWTVerificationException {
        return new Claims(jwtVerifier.verify(token));
    }

    static public class Claims {
        Id<Member, Integer> memberId;
        Nickname nickname;
        Email email;
        String[] roles;
        Date iat;
        Date exp;

        private Claims() {
        }

        Claims(DecodedJWT decodedJWT) {
            Claim memberId = decodedJWT.getClaim("memberId");
            if (!memberId.isNull())
                this.memberId = memberId.as(Id.class);
            Claim nickname = decodedJWT.getClaim("nickname");
            if (!nickname.isNull())
                this.nickname = new Nickname(nickname.asString());
            Claim email = decodedJWT.getClaim("email");
            if (!email.isNull())
                this.email = new Email(email.asString());
            Claim roles = decodedJWT.getClaim("roles");
            if (!roles.isNull())
                this.roles = roles.asArray(String.class);
            this.iat = decodedJWT.getIssuedAt();
            this.exp = decodedJWT.getExpiresAt();
        }

        public static Claims of(Id<Member, Integer> memberId, Nickname nickname, Email email, String[] roles) {
            Claims claims = new Claims();
            claims.memberId = memberId;
            claims.nickname = nickname;
            claims.email = email;
            claims.roles = roles;
            return claims;
        }

        long iat() {
            return iat != null ? iat.getTime() : -1;
        }

        long exp() {
            return exp != null ? exp.getTime() : -1;
        }

        void eraseIat() {
            iat = null;
        }

        void eraseExp() {
            exp = null;
        }
    }
}
