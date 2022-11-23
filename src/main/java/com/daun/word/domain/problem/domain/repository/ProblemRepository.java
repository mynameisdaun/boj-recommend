package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.vo.Tier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProblemRepository {

    Problem save(final Problem request);

    Optional<Problem> findById(final Integer id);

    List<Problem> findAllByIdIn(final List<Integer> ids);

    int countByTier(final Tier tier);
}
