package com.daun.word.global.dto;

import com.daun.word.global.vo.Tier;
import lombok.Data;

@Data
public class TierDTO {

    private final int level;
    private final String rate;

    public TierDTO (Tier tier) {
        this.level = tier.getLevel();
        this.rate = tier.getRate();
    }
}
