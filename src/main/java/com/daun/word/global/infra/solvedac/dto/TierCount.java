package com.daun.word.global.infra.solvedac.dto;

import com.daun.word.global.vo.Tier;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TierCount {
    private Tier level;
    private int count;

    public Tier getTier() {
        return this.level;
    }
}
