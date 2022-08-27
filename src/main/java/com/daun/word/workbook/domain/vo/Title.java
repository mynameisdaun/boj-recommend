package com.daun.word.workbook.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.daun.word.utils.StringUtils.isNullOrBlank;

@Getter
@EqualsAndHashCode
@ToString
public class Title {

    private final String title;

    public Title(String title) {
        if (isNullOrBlank(title)) {
            throw new IllegalArgumentException("정책에 맞지 않는 제목 입니다.");
        }
        this.title = title;
    }

    public String getValue() {
        return this.title;
    }
}
