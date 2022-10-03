package com.daun.word.problem.domain.repository.vo;

import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Tier {

    private final Integer tier;

    public Tier(Integer tier) {
        Preconditions.checkArgument(tier != null || tier > 0, "올바르지 않은 티어 숫자 입니다");
        this.tier = tier;
    }

    public Integer getValue() {
        return this.tier;
    }
}

