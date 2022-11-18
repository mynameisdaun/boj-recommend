package com.daun.word.domain.problem.dto;

import com.daun.word.global.vo.Tier;
import com.google.common.base.Preconditions;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManualUpdateRequest {
    private Tier minTier;
    private Tier maxTier;

    public ManualUpdateRequest(Tier minTier, Tier maxTier) {
        Preconditions.checkArgument(minTier.getLevel() <= maxTier.getLevel());
        this.minTier = minTier;
        this.maxTier = maxTier;
    }

    public int max() {
        return maxTier.getLevel();
    }

    public int min() {
        return minTier.getLevel();
    }
}
