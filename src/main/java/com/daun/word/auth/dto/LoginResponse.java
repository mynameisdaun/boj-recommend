package com.daun.word.auth.dto;

import com.daun.word.member.domain.Member;
import com.daun.word.auth.token.domain.Token;
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
