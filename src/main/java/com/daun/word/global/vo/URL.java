package com.daun.word.global.vo;

import com.google.common.base.Preconditions;
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
public class URL {

    @Column(name = "url", nullable = false)
    private String url;

    public URL(Integer id) {
        Preconditions.checkArgument(id != null && id > 0, "유효하지 않은 문제 번호 입니다.");
        this.url = "https://www.acmicpc.net/problem/" + id;
    }

    public String getValue() {
        return this.url;
    }

}
