package com.daun.word.global.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static com.daun.word.global.utils.StringUtils.isNullOrBlank;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Title {

    @Column(name = "title", nullable = false)
    private String title;

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
