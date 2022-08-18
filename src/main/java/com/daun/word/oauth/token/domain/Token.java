package com.daun.word.oauth.token.domain;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.SocialType;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(exclude = "accessTokenExpiredDate, refreshTokenExpiredDate, socialAccessTokenExpiredDate, socialRefreshTokenExpiredDate, createdAt, updatedAt")
@ToString
public class Token {
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

    public String getEmail () {
        return this.email.getValue();
    }

    protected void setEmail(String email) {
        this.email = new Email(email);
    }

    public String getMemberSocialType() {
        return memberSocialType.name();
    }

    public void setMemberSocialType(String socialType) {
        this.memberSocialType = SocialType.valueOf(socialType);
    }
}
