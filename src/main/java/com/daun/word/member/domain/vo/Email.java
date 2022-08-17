package com.daun.word.member.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
@ToString
public class Email implements Serializable {
    private final String email;

    public Email(String email) {
        String pattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(!Pattern.matches(pattern, email)) {
            throw new IllegalArgumentException("형식에 맞지 않는 이메일 입니다.");
        }
        this.email = email;
    }

    public String getValue() {
        return this.email;
    }
}
