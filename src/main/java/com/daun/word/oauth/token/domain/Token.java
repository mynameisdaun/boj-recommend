package com.daun.word.oauth.token.domain;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.SocialType;
import lombok.*;

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

        if (memberEmail != null ? !memberEmail.equals(token.memberEmail) : token.memberEmail != null) return false;
        if (accessToken != null ? !accessToken.equals(token.accessToken) : token.accessToken != null) return false;
        if (accessTokenExpiredDate != null ? !accessTokenExpiredDate.equals(token.accessTokenExpiredDate) : token.accessTokenExpiredDate != null)
            return false;
        if (refreshToken != null ? !refreshToken.equals(token.refreshToken) : token.refreshToken != null) return false;
        if (refreshTokenExpiredDate != null ? !refreshTokenExpiredDate.equals(token.refreshTokenExpiredDate) : token.refreshTokenExpiredDate != null)
            return false;
        if (memberSocialType != token.memberSocialType) return false;
        if (socialAccessToken != null ? !socialAccessToken.equals(token.socialAccessToken) : token.socialAccessToken != null)
            return false;
        if (socialAccessTokenExpiredDate != null ? !socialAccessTokenExpiredDate.equals(token.socialAccessTokenExpiredDate) : token.socialAccessTokenExpiredDate != null)
            return false;
        if (socialRefreshToken != null ? !socialRefreshToken.equals(token.socialRefreshToken) : token.socialRefreshToken != null)
            return false;
        return socialRefreshTokenExpiredDate != null ? socialRefreshTokenExpiredDate.equals(token.socialRefreshTokenExpiredDate) : token.socialRefreshTokenExpiredDate == null;
    }

    @Override
    public int hashCode() {
        int result = memberEmail != null ? memberEmail.hashCode() : 0;
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (accessTokenExpiredDate != null ? accessTokenExpiredDate.hashCode() : 0);
        result = 31 * result + (refreshToken != null ? refreshToken.hashCode() : 0);
        result = 31 * result + (refreshTokenExpiredDate != null ? refreshTokenExpiredDate.hashCode() : 0);
        result = 31 * result + (memberSocialType != null ? memberSocialType.hashCode() : 0);
        result = 31 * result + (socialAccessToken != null ? socialAccessToken.hashCode() : 0);
        result = 31 * result + (socialAccessTokenExpiredDate != null ? socialAccessTokenExpiredDate.hashCode() : 0);
        result = 31 * result + (socialRefreshToken != null ? socialRefreshToken.hashCode() : 0);
        result = 31 * result + (socialRefreshTokenExpiredDate != null ? socialRefreshTokenExpiredDate.hashCode() : 0);
        return result;
    }
}
