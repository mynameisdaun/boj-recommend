package com.daun.word.oauth.token.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenDTO {
    private String memberId;
    private String accessToken;
    private LocalDateTime accessTokenExpiredDate;
    private String refreshToken;
    private LocalDateTime refreshTokenExpiredDate;
    private String memberSocialType;
    private String socialAccessToken;
    private LocalDateTime socialAccessTokenExpiredDate;
    private String socialRefreshToken;
    private LocalDateTime socialRefreshTokenExpiredDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
