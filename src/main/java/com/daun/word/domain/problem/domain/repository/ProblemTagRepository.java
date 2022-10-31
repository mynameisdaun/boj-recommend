package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.ProblemTag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProblemTagRepository {

    ProblemTag save(final ProblemTag request);

    Optional<ProblemTag> findById(final Long id);

    List<ProblemTag> findProblemTagsByProblem(final Problem problem);
}
