package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProblemRepository {

    Problem save(final Problem request);

    Optional<Problem> findById(final Long id);
}
