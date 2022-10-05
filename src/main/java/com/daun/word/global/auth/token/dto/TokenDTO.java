package com.daun.word.global.auth.token.dto;

import com.daun.word.global.auth.token.domain.Token;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.SocialType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenDTO {
    private Email email;
    private String accessToken;
    private LocalDateTime accessTokenExpiredDate;
    private String refreshToken;
    private LocalDateTime refreshTokenExpiredDate;
    private SocialType memberSocialType;
    private String socialAccessToken;
    private LocalDateTime socialAccessTokenExpiredDate;
    private String socialRefreshToken;
    private LocalDateTime socialRefreshTokenExpiredDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TokenDTO (Token token) {
        this.email = token.getMemberEmail();
        this.accessToken = token.getAccessToken();
        this.accessTokenExpiredDate = token.getAccessTokenExpiredDate();
        this.refreshToken = token.getRefreshToken();
        this.refreshTokenExpiredDate = token.getRefreshTokenExpiredDate();
        this.memberSocialType = token.getMemberSocialType();
        this.socialAccessToken = token.getSocialAccessToken();
        this.socialAccessTokenExpiredDate = token.getSocialAccessTokenExpiredDate();
        this.socialRefreshToken = token.getSocialRefreshToken();
        this.socialRefreshTokenExpiredDate = token.getSocialRefreshTokenExpiredDate();
        this.createdAt = token.getCreatedAt();
        this.updatedAt = token.getUpdatedAt();
    }
}
