package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.recommend.dto.search.RecommendSearchQuery;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class FakeProblemQueryRepository implements ProblemQueryRepository{
    @Override
    public List<Problem> search(RecommendSearchQuery query) {
        throw new NotImplementedException("아직 구현 전입니다.ㅠㅠ");
    }
}
