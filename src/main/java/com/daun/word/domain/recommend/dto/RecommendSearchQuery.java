package com.daun.word.domain.recommend.dto;

import com.daun.word.global.vo.Tier;
import lombok.Getter;


@Getter
public class RecommendSearchQuery {

    private Tier minTier;
    private Tier maxTier;

    public RecommendSearchQuery(int minTier, int maxTier) {
        this.minTier = new Tier(minTier);
        this.maxTier = new Tier(maxTier);
    }
}
