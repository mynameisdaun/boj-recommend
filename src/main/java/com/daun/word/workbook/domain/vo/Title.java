package com.daun.word.workbook.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.daun.word.utils.StringUtils.isNullOrBlank;

@Getter
@EqualsAndHashCode
@ToString
public class Title {
    private final String author;

    public Title(String author) {
        if (isNullOrBlank(author)) {
            throw new IllegalArgumentException("정책에 맞지 않는 단어장 제목 입니다.");
        }
        this.author = author;
    }

    public String getValue() {
        return this.author;
    }
}
