package com.daun.word.domain.recommend.domain.strategy;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.recommend.dto.RecommendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TierProblemPoolStrategy implements ProblemPoolStrategy {

    @Override
    public List<Problem> recommend(ProblemRepository problemRepository, RecommendRequest request) {
        return problemRepository.findAllByTierBetweenOrderByAcceptedUserCountDesc(request.getMin(), request.getMax());
    }
}
