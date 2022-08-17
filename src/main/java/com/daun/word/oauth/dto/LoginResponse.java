package com.daun.word.oauth.dto;

import com.daun.word.member.domain.Member;
import com.daun.word.oauth.token.domain.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponse {
    private Member member;
    private Token token;
}
