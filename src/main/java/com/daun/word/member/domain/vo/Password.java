package com.daun.word.member.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@EqualsAndHashCode
@ToString
public class Password {
    private final String password;

    // 적어도 8문자 이상
    // 한개 이상의 숫자 포함
    // 적어도 하나 이상의 소문자, 대문자 영어
    // !@#%$^ 중 하나의 특수문자
    // 스페이스바나, 탭을 선택하지 않음
    public Password(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$";
        checkArgument(Pattern.matches(pattern, password), "올바르지 않은 비밀번호 입니다.");
        this.password = password;
    }

    public String getValue() {
        return this.password;
    }

}
