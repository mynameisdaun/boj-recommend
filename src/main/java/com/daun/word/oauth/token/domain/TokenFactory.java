package com.daun.word.oauth.token.domain;

import com.daun.word.infra.kakao.dto.SocialTokenResponse;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class TokenFactory {

    private final JwtUtils jwtUtils;

    public Token generateToken(Member member, SocialTokenResponse socialTokenResponse) {
        Integer memberId = member.getId();
        String accessToken = jwtUtils.accessToken(memberId);
        LocalDateTime accessTokenExpiredDate = LocalDateTime.now().plusSeconds(jwtUtils.getAccessExpiresIn() / 1000);
        String refreshToken = jwtUtils.refreshToken(memberId);
        LocalDateTime refreshTokenExpiredDate = LocalDateTime.now().plusSeconds(jwtUtils.getRefreshExpiresIn() / 1000);
        SocialType memberSocialType = SocialType.valueOf(member.getSocialType());
        String socialAccessToken = socialTokenResponse.getAccess_token();
        LocalDateTime socialAccessTokenExpiredDate = LocalDateTime.now().plusSeconds(socialTokenResponse.getAccess_token_expires_in());
        String socialRefreshToken = socialTokenResponse.getRefresh_token();
        LocalDateTime socialRefreshTokenExpiredDate = LocalDateTime.now().plusSeconds(socialTokenResponse.getRefresh_token_expires_in());

        return new Token(
                memberId,
                accessToken,
                accessTokenExpiredDate,
                refreshToken,
                refreshTokenExpiredDate,
                memberSocialType,
                socialAccessToken,
                socialAccessTokenExpiredDate,
                socialRefreshToken,
                socialRefreshTokenExpiredDate,
                null,
                null
        );
    }

}
