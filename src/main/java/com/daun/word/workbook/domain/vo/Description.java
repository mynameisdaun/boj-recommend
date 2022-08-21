package com.daun.word.workbook.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.daun.word.utils.StringUtils.isNullOrBlank;

@Getter
@EqualsAndHashCode
@ToString
public class Description {
    private final String description;

    public Description(String description) {
        //TODO: 단어장 설명 최소, 최대 글자 크기 지정해 줘야 하지 않을까나?
        if (isNullOrBlank(description)) {
            throw new IllegalArgumentException("정책에 맞지 않는 단어장 설명 입니다.");
        }
        this.description = description;
    }

    public String getValue() {
        return this.description;
    }
}
