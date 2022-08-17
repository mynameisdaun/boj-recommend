package com.daun.word.infra.kakao.dto;

import lombok.Data;
import lombok.Setter;

@Setter
public class KakaoTokenResponse {
    private String token_type; //토큰타입, bearer 고정
    private String access_token; // 사용자 액세스 토큰
    private String expires_in; // 초단위 토큰 만료시간
    private String refresh_token; // 리프레쉬 토큰
    private String refresh_token_expires_in; // 리프레시 토큰 만료시간(초)

    public String getTokenType() {
        return token_type;
    }

    public String getAccessToken() {
        return access_token;
    }

    public String getExpiresIn() {
        return expires_in;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public String getRefreshTokenExpiresIn() {
        return refresh_token_expires_in;
    }
}
