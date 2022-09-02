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
    private Token token;

    public LoginResponse(Member member, Token token) {
        if (member == null || token == null) {
            throw new IllegalArgumentException("회원정보와 토큰 정보가 없으면 로그인 응답을 할 수 없습니다.");
        }
        this.member = member;
        this.token = token;
    }
}
