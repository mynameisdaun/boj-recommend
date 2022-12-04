package com.daun.word.domain.member.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Password;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RegisterAuthRequest {

    @NotNull
    private Email email;

    @NotNull
    private Password password;

    public RegisterAuthRequest(String email, String password) {
        this.email = new Email(email);
        this.password = new Password(password);
    }



}
