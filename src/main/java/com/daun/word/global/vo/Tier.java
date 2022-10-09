package com.daun.word.global.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
public final class Tier {

    private static final Map<Integer, String> level_rate = new HashMap<>();
    private final int level;
    private final String rate;

    static {
        level_rate.put(0, "UNRATED");
        level_rate.put(1, "b5");
        level_rate.put(2, "b4");
        level_rate.put(3, "b3");
        level_rate.put(4, "b2");
        level_rate.put(5, "b1");
        level_rate.put(6, "s5");
        level_rate.put(7, "s4");
        level_rate.put(8, "s3");
        level_rate.put(9, "s2");
        level_rate.put(10, "s1");
        level_rate.put(11, "g5");
        level_rate.put(12, "g4");
        level_rate.put(13, "g3");
        level_rate.put(14, "g2");
        level_rate.put(15, "g1");
        level_rate.put(16, "p5");
        level_rate.put(17, "p4");
        level_rate.put(18, "p3");
        level_rate.put(19, "p2");
        level_rate.put(20, "p1");
        level_rate.put(21, "d5");
        level_rate.put(22, "d4");
        level_rate.put(23, "d3");
        level_rate.put(24, "d2");
        level_rate.put(25, "d1");
        level_rate.put(26, "r5");
        level_rate.put(27, "r4");
        level_rate.put(28, "r3");
        level_rate.put(29, "r2");
        level_rate.put(30, "r1");
    }

    public Tier(int level) {
        this.level = level;
        this.rate = level_rate.get(level);
    }

    public Tier minus(int minus) {
        return new Tier(this.level - minus);
    }

    public Tier plus(int plus) {
        return new Tier(this.level + plus);
    }
}

