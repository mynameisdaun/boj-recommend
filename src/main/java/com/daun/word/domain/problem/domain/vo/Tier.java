package com.daun.word.domain.problem.domain.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Tier {

    UNRATED(0, "UNRATED"),
    BRONZE_5(1, "BRONZE_5"),
    BRONZE_4(2, "BRONZE_4"),
    BRONZE_3(3, "BRONZE_3"),
    BRONZE_2(4, "BRONZE_2"),
    BRONZE_1(5, "BRONZE_1"),
    SILVER_5(6, "SILVER_5"),
    SILVER_4(6, "SILVER_5"),

    ;

    private int level;
    private String rate;

    Tier(int level, String rate) {
        this.level = level;
        this.rate = rate;
    }
}

