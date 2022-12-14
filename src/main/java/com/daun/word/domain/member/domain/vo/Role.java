package com.daun.word.domain.member.domain.vo;

public enum Role {
    ROLE_GUEST("ROLE_GUEST"),
    ROLE_TEMP("ROLE_TEMP"),
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}
