package com.daun.word.member.dto;

import com.daun.word.member.domain.SocialType;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RegisterRequest {
    private Nickname nickname;
    private String password;
    private Email email;
    private SocialType socialType;


    public RegisterRequest(Nickname nickname, Email email, SocialType socialType) {
        if (nickname == null || email == null || socialType == null) {
            throw new IllegalArgumentException("닉네임, 이메일, 소셜타입은 회원가입하기 위한 필수 조건입니다.");
        }
        this.nickname = nickname;
        this.password = UUID.randomUUID().toString();
        this.email = email;
        this.socialType = socialType;
    }
}
