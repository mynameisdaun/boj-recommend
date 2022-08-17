package com.daun.word.oauth.token.domain;

import com.daun.word.infra.kakao.dto.SocialTokenResponse;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.SocialType;
import com.daun.word.utils.JwtUtils;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class Token {

    private Integer memberId;
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

    public String getMemberSocialType() {
        return memberSocialType.name();
    }

    public void setMemberSocialType(String socialType) {
        this.memberSocialType = SocialType.valueOf(socialType);
    }
}
