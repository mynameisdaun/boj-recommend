package com.daun.word.auth.dto;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Password;
import com.daun.word.member.domain.vo.SocialType;
import lombok.Data;

import static com.google.common.base.Preconditions.checkArgument;

@Data
public class AuthenticationRequest {

    private final Email email;

    private final Password password;

    private final SocialType socialType;

    public AuthenticationRequest(Email email, Password password, SocialType socialType) {
        checkArgument(email != null, "이메일 주소는 필수 값 입니다.");
        checkArgument(password != null, "비밀번호는 필수 값 입니다.");
        checkArgument(socialType != null, "소셜타입은 필수 값 입니다.");
        this.email = email;
        this.password = password;
        this.socialType = socialType;
    }
}
