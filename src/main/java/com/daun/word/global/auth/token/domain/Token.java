package com.daun.word.global.auth.token.domain;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.SocialType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Token {
    private Email memberEmail;
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


    public Token(Email memberEmail, String accessToken, LocalDateTime accessTokenExpiredDate, String refreshToken, LocalDateTime refreshTokenExpiredDate, SocialType memberSocialType, String socialAccessToken, LocalDateTime socialAccessTokenExpiredDate, String socialRefreshToken, LocalDateTime socialRefreshTokenExpiredDate) {
        this.memberEmail = memberEmail;
        this.accessToken = accessToken;
        this.accessTokenExpiredDate = accessTokenExpiredDate;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiredDate = refreshTokenExpiredDate;
        this.memberSocialType = memberSocialType;
        this.socialAccessToken = socialAccessToken;
        this.socialAccessTokenExpiredDate = socialAccessTokenExpiredDate;
        this.socialRefreshToken = socialRefreshToken;
        this.socialRefreshTokenExpiredDate = socialRefreshTokenExpiredDate;
    }

    protected void setMemberEmail(String memberEmail) {
        this.memberEmail = new Email(memberEmail);
    }

    public void setMemberSocialType(String socialType) {
        this.memberSocialType = SocialType.valueOf(socialType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        return memberEmail != null ? memberEmail.equals(token.memberEmail) : token.memberEmail == null;
    }

    @Override
    public int hashCode() {
        return memberEmail != null ? memberEmail.hashCode() : 0;
    }
}
