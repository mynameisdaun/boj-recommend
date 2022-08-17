package com.daun.word.infra.kakao.client;

import com.daun.word.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.infra.kakao.dto.KakaoTokenResponse;
import org.springframework.stereotype.Service;

@Service
public interface KakaoOAuthClient {
    /* authCode 로 AccessToken 얻기 */
    KakaoTokenResponse token(String authCode);

    /* accessToken으로 유저 프로필 얻기 */
    KakaoProfileResponse profile(String accessToken);
}
