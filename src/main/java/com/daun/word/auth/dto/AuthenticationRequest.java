package com.daun.word.auth.dto;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Password;
import lombok.Data;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Data
public class AuthenticationRequest {

    private Email email;

    private Password password;

    public AuthenticationRequest(String principal, String credentials) {
        checkArgument(isNotEmpty(principal), "email 주소는 필수 값 입니다.");
        checkArgument(isNotEmpty(credentials), "password는 필수 값 입니다.");
        this.email = new Email(principal);
        this.password = new Password(credentials);
    }
}
