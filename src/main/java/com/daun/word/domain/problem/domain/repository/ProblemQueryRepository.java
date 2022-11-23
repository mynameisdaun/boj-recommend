package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.recommend.dto.search.RecommendSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemQueryRepository {
    List<Problem> search(final RecommendSearchQuery query);
}
