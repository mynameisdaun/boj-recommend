package com.daun.word.infra.kakao.dto;

import lombok.Getter;

@Getter
public class SocialTokenResponse {
    private String token_type; //토큰타입, bearer 고정
    private String access_token; // 사용자 액세스 토큰
    private long access_token_expires_in; // 초단위 토큰 만료시간
    private String refresh_token; // 리프레쉬 토큰
    private long refresh_token_expires_in;
}
