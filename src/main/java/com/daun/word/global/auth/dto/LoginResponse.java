package com.daun.word.global.auth.dto;

import com.daun.word.domain.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginResponse {
    private Member member;
    private String accessToken;
    private int accessTokenExpiresIn;
    private String refreshToken;
    private int refreshTokenExpiresIn;
}
