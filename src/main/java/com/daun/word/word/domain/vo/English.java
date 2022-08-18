package com.daun.word.word.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.daun.word.utils.StringUtils.isNullOrBlank;

@Getter
@EqualsAndHashCode
@ToString
public class English {

    private final String english;

    public English(String english) {
        if(isNullOrBlank(english)) {
            throw new IllegalArgumentException("단어의 영어 표기는 한글자 이상이어야 합니다.");
        }
        this.english = english;
    }

    public String getValue() {
        return this.english;
    }
}
