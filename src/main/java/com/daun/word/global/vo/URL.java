package com.daun.word.global.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.daun.word.global.utils.StringUtils.isNullOrBlank;

@Getter
@EqualsAndHashCode
@ToString
public class URL {

    private final String url;

    public URL(String url) {
        if (isNullOrBlank(url)) {
            throw new IllegalArgumentException("정책에 맞지 않는 url 입니다.");
        }
        this.url = url;
    }

    public String getValue() {
        return this.url;
    }
}
