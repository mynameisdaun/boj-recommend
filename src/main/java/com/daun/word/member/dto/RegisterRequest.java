package com.daun.word.member.dto;

import com.daun.word.member.domain.SocialType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static com.daun.word.utils.StringUtils.isNullOrBlank;

@Getter
@Setter
public class RegisterRequest {
    //TODO: 회원가입 정책 세우기, 현재는 닉네임 이메일, 소셜타입 3개만 최소 필요 조건
    private String nickname;
    private String password;
    private String email;
    private SocialType socialType;


    public RegisterRequest(String nickname, String email, SocialType socialType) {
        if(isNullOrBlank(nickname) || isNullOrBlank(email) || socialType == null) {
           throw new IllegalArgumentException("닉네임, 이메일, 소셜타입은 회원가입하기 위한 필수 조건입니다.");
        }
        this.nickname = nickname;
        this.password = UUID.randomUUID().toString();
        this.email = email;
        this.socialType = socialType;
    }
}
