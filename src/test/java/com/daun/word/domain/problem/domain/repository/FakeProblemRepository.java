package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;

import java.util.Optional;

public class FakeProblemRepository implements ProblemRepository{

    @Override
    public Problem save(Problem request) {
        return null;
    }

    @Override
    public Optional<Problem> findById(Integer id) {
        return Optional.empty();
    }
}
