package com.daun.word.global.auth.token.domain;

import com.daun.word.config.security.Jwt;
import com.daun.word.global.infra.kakao.dto.SocialTokenResponse;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Role;
import com.daun.word.domain.member.domain.vo.SocialType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@AllArgsConstructor
public class TokenFactory {

    private final Jwt jwt;

    //TODO: role
    public Token token(Member member) {
        String accessToken = member.accessToken(jwt, new String[]{Role.USER.name()});
        LocalDateTime accessTokenExpiredDate = LocalDateTime.now().plusSeconds(jwt.getExpirySeconds());
        String refreshToken = UUID.randomUUID().toString();
        LocalDateTime refreshTokenExpiredDate = LocalDateTime.now().plusWeeks(1L);
        SocialType memberSocialType = SocialType.valueOf(member.getSocialType().name());

        return new Token(
                member.getEmail(),
                accessToken,
                accessTokenExpiredDate,
                refreshToken,
                refreshTokenExpiredDate,
                memberSocialType,
                null,
                null,
                null,
                null
        );
    }

    public Token tokenWithSocial(Member member, SocialTokenResponse socialTokenResponse) {
        String accessToken = member.accessToken(jwt, new String[]{Role.USER.name()});
        LocalDateTime accessTokenExpiredDate = LocalDateTime.now().plusSeconds(jwt.getExpirySeconds());
        String refreshToken = UUID.randomUUID().toString();
        LocalDateTime refreshTokenExpiredDate = LocalDateTime.now().plusWeeks(1L);
        SocialType memberSocialType = SocialType.valueOf(member.getSocialType().name());
        String socialAccessToken = socialTokenResponse.getAccess_token();
        LocalDateTime socialAccessTokenExpiredDate = LocalDateTime.now().plusSeconds(socialTokenResponse.getAccess_token_expires_in());
        String socialRefreshToken = socialTokenResponse.getRefresh_token();
        LocalDateTime socialRefreshTokenExpiredDate = LocalDateTime.now().plusSeconds(socialTokenResponse.getRefresh_token_expires_in());

        return new Token(
                member.getEmail(),
                accessToken,
                accessTokenExpiredDate,
                refreshToken,
                refreshTokenExpiredDate,
                memberSocialType,
                socialAccessToken,
                socialAccessTokenExpiredDate,
                socialRefreshToken,
                socialRefreshTokenExpiredDate
        );
    }

}
