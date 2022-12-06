package com.daun.word.domain.problem.dto.search;

public enum SortType {
    ACCEPTED_USER_COUNT("solved");

    private String value;

    SortType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
