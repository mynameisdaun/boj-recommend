package com.daun.word.domain.recommend.domain.strategy;

import com.daun.word.domain.recommend.domain.vo.RecommendType;


public class ProblemPoolStrategyFactory {

    public static ProblemPoolStrategy create(RecommendType recommendType) {
        if (recommendType.equals(RecommendType.TIER)) {
            return new TierProblemPoolStrategy();
        }
        throw new IllegalArgumentException("사용할 수 없는 문제 풀 생성 전략입니다.");
    }
}
