package com.daun.word.auth.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Password;
import com.daun.word.domain.member.domain.vo.SocialType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.google.common.base.Preconditions.checkArgument;

@Data
@NoArgsConstructor
public class AuthRequest {
    @NotNull(message = "이메일 주소를 입력해 주세요.")
    private Email email;
    @NotNull(message = "비밀번호를 입력해 주세요.")
    private Password password;

    public AuthRequest(String email, String password) {
        this.email = new Email(email);
        this.password = new Password(password);
    }
}
