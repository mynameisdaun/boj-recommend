package com.daun.word.domain.reference.domain.vo;

import lombok.Getter;

@Getter
public enum ReferenceType {
    ARTICLE("article"),
    MEMBER("member"),
    WORKBOOK("workbook");

    private String value;

    ReferenceType(String value) {
        this.value = value;
    }

    public static ReferenceType of(String value) {
        for (ReferenceType rt : ReferenceType.values()) {
            if (rt.getValue().equals(value)) {
                return rt;
            }
        }
        throw new IllegalArgumentException();
    }
}
