package com.daun.word.global.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Name {
    @Column(name = "name")
    private String name;

    public Name(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }
}
