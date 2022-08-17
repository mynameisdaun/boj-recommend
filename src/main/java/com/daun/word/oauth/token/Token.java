package com.daun.word.oauth.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Component
public class Token {
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
