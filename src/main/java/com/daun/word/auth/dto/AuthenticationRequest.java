package com.daun.word.auth.dto;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Password;
import com.daun.word.member.domain.vo.SocialType;
import lombok.Data;

import javax.validation.constraints.NotNull;

import static com.google.common.base.Preconditions.checkArgument;

@Data
public class AuthenticationRequest {

    @NotNull(message = "이메일 주소를 입력해 주세요.")
    private final Email email;
    @NotNull(message = "비밀번호를 입력해 주세요.")
    private final Password password;
    @NotNull(message = "회원 유형을 지정해 주세요.")
    private final SocialType socialType;

    public AuthenticationRequest(String email, String password, String socialType) {
        this.email = new Email(email);
        this.password = new Password(password);
        this.socialType = SocialType.valueOf(socialType);
    }
}
