package com.daun.word.global.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Name {
    private final String name;

    public Name(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }
}
