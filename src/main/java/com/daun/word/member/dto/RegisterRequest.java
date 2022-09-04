package com.daun.word.member.dto;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import com.daun.word.member.domain.vo.Password;
import com.daun.word.member.domain.vo.SocialType;
import lombok.Data;

import static com.google.common.base.Preconditions.checkArgument;

@Data
public class RegisterRequest {
    private Email email;
    private Password password;
    private Nickname nickname;
    private SocialType socialType;
    
    public RegisterRequest(Email email, Password password, Nickname nickname, SocialType socialType) {
        checkArgument(email != null, "이메일은 회원가입하기 위한 필수 조건입니다");
        checkArgument(password != null, "이메일은 회원가입하기 위한 필수 조건입니다");
        checkArgument(nickname != null, "이메일은 회원가입하기 위한 필수 조건입니다");
        checkArgument(socialType != null, "이메일은 회원가입하기 위한 필수 조건입니다");
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.socialType = socialType;
    }
}
