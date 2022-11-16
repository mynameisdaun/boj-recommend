package com.daun.word.domain.recommend.domain.strategy;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.recommend.dto.RecommendSearchQuery;

import java.util.List;

public interface ProblemPoolStrategy {
    List<Problem> recommend(ProblemRepository problemRepository, RecommendSearchQuery query);

}
