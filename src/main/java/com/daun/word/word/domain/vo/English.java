package com.daun.word.word.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

import static com.daun.word.utils.StringUtils.isNullOrBlank;

@Getter
@EqualsAndHashCode
@ToString
public class English {

    private final String english;

    public English(String english) {
        if (isNullOrBlank(english)) {
            throw new IllegalArgumentException("단어의 영어 표기는 한글자 이상이어야 합니다.");
        }
        if (!Pattern.matches("^[a-zA-Z-_\\.\\s]*$", english)) {
            throw new IllegalArgumentException("영어 표기는 영문자만 포함하고 있어야 합니다.");
        }
        this.english = english.trim();
    }

    public String getValue() {
        return this.english;
    }
}
