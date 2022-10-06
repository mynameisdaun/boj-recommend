package com.daun.word.domain.problem.domain.vo;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public final class Tier {

    private static final Map<Integer, String> level_rate = new HashMap<>();
    private final int level;
    private final String rate;

    static {
        level_rate.put(0, "UNRATED");
        level_rate.put(1, "BRONZE_5");
        level_rate.put(2, "BRONZE_4");
        level_rate.put(3, "BRONZE_3");
        level_rate.put(4, "BRONZE_2");
        level_rate.put(5, "BRONZE_1");
        level_rate.put(6, "SILVER_5");
        level_rate.put(7, "SILVER_4");
        level_rate.put(8, "SILVER_3");
        level_rate.put(9, "SILVER_2");
        level_rate.put(10, "SILVER_1");
        level_rate.put(11, "GOLD_5");
        level_rate.put(12, "GOLD_4");
        level_rate.put(13, "GOLD_3");
        level_rate.put(14, "GOLD_2");
        level_rate.put(15, "GOLD_1");
        level_rate.put(16, "PLATINUM_5");
        level_rate.put(17, "PLATINUM_4");
        level_rate.put(18, "PLATINUM_3");
        level_rate.put(19, "PLATINUM_2");
        level_rate.put(20, "PLATINUM_1");
        level_rate.put(21, "DIAMOND_5");
        level_rate.put(22, "DIAMOND_4");
        level_rate.put(23, "DIAMOND_3");
        level_rate.put(24, "DIAMOND_2");
        level_rate.put(25, "DIAMOND_1");
        level_rate.put(26, "RUBY_5");
        level_rate.put(27, "RUBY_4");
        level_rate.put(28, "RUBY_3");
        level_rate.put(29, "RUBY_2");
        level_rate.put(30, "RUBY_1");
    }

    public Tier(int level) {
        this.level = level;
        this.rate = level_rate.get(level);
    }

}

