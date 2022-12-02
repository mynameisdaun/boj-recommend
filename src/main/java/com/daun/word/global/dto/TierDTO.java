package com.daun.word.global.dto;

import com.daun.word.global.vo.Tier;
import lombok.Data;

import java.io.Serializable;

@Data
public class TierDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int level;
    private final String rate;

    public TierDTO(Tier tier) {
        this.level = tier.getLevel();
        this.rate = tier.getRate();
    }
}
