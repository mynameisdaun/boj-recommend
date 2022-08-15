package com.daun.word.oauth.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KakaoAccessTokenResponse {
    private String token_type; //토큰타입, bearer 고정
    private String access_token; // 사용자 액세스 토큰
    private String expires_in; // 초단위 토큰 만료시간
    private String refresh_token; // 리프레쉬 토큰
    private String refresh_token_expires_in; // 리프레시 토큰 만료시간(초)
}
