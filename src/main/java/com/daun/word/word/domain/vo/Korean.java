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

        if (!Pattern.matches("^[a-zA-Zㄱ-ㅎ가-힣-_\\s\\.]+$", korean)) {
            throw new IllegalArgumentException("한국어 표기는 한글만 포함하고 있어야 합니다.");
        }
        this.korean = korean;
    }

    public String getValue() {
        return this.korean;
    }

}
