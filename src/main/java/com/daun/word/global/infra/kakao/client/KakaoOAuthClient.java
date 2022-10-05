package com.daun.word.global.infra.kakao.client;

import com.daun.word.global.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.global.infra.kakao.dto.KakaoTokenResponse;
import org.springframework.stereotype.Service;

@Service
public interface KakaoOAuthClient {
    /* authCode 로 AccessToken 얻기 */
    KakaoTokenResponse token(String authCode, String restApiKey, String redirectUri);

    /* accessToken으로 유저 프로필 얻기 */
    KakaoProfileResponse profile(String accessToken);
}
