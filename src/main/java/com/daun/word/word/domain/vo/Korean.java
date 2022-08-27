package com.daun.word.word.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

import static com.daun.word.utils.StringUtils.isNullOrBlank;

@Getter
@EqualsAndHashCode
@ToString
public class Korean {

    private final String korean;

    public Korean(String korean) {
        if (isNullOrBlank(korean)) {
            throw new IllegalArgumentException("단어의 한글 표기는 한글자 이상이어야 합니다.");
        }

        if (!Pattern.matches("^[0-9a-zA-Zㄱ-ㅎ가-힣-_\\s\\.\\~…\\(\\)\\,\\=]+$", korean)) {
            throw new IllegalArgumentException("올바르지 않은 한국어 표기입니다.");
        }
        this.korean = korean.trim();
    }

    public String getValue() {
        return this.korean;
    }

}
