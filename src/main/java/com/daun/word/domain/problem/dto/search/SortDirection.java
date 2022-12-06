package com.daun.word.domain.problem.dto.search;

public enum SortDirection {
    ASC("asc"),
    DESC("desc");

    private String value;

    SortDirection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
