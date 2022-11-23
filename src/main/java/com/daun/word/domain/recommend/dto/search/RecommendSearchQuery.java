package com.daun.word.domain.recommend.dto.search;

import com.daun.word.global.vo.Tier;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RecommendSearchQuery {

    private Tier minTier;
    private Tier maxTier;

    private SortType sort;

    private SortDirection direction;


    public RecommendSearchQuery(int minTier, int maxTier) {
        this.minTier = new Tier(minTier);
        this.maxTier = new Tier(maxTier);
        if (this.minTier != null && this.maxTier != null && minTier > maxTier) {
            throw new IllegalStateException("최대 티어는 최소 티어보다 같거나 커야 합니다");
        }
        this.sort = SortType.ACCEPTED_USER_COUNT;
        this.direction = SortDirection.DESC;
    }
}
