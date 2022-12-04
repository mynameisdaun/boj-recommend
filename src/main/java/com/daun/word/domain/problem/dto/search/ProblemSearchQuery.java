package com.daun.word.domain.problem.dto.search;

import com.daun.word.global.vo.Tier;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProblemSearchQuery {

    private Tier minTier;
    private Tier maxTier;

    private Integer minAcceptedUserCount;
    private SortType sort;
    private SortDirection direction;

    public ProblemSearchQuery(int minTier, int maxTier, int minAcceptedUserCount, SortType sort, SortDirection direction) {
        this.minTier = new Tier(minTier);
        this.maxTier = new Tier(maxTier);
        if (this.minTier != null && this.maxTier != null && minTier > maxTier) {
            throw new IllegalStateException("최대 티어는 최소 티어보다 같거나 커야 합니다");
        }
        this.minAcceptedUserCount = minAcceptedUserCount;
        this.sort = sort;
        this.direction = direction;
    }

    public ProblemSearchQuery(int minTier, int maxTier, int minAcceptedUserCount) {
        this(minTier, maxTier, minAcceptedUserCount, SortType.ACCEPTED_USER_COUNT, SortDirection.DESC);
    }

    public ProblemSearchQuery(int minTier, int maxTier) {
        this(minTier, maxTier, 0);
    }
}
