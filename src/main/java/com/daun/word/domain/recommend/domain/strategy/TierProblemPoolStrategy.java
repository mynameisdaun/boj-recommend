package com.daun.word.domain.recommend.domain.strategy;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.recommend.dto.RecommendSearchQuery;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
public class TierProblemPoolStrategy implements ProblemPoolStrategy {
    @Override
    public List<Problem> recommend(ProblemRepository problemRepository, RecommendSearchQuery query) {
        Preconditions.checkArgument(query.getMinTier() != null, "최소 티어가 없으면, 티어 추천 전략을 사용할 수 없습니다");
        Preconditions.checkArgument(query.getMaxTier() != null, "최대 티어가 없으면, 티어 추천 전략을 사용할 수 없습니다");
        return problemRepository.findAllByTierBetweenOrderByAcceptedUserCountDesc(query.getMinTier(), query.getMaxTier());
    }
}
