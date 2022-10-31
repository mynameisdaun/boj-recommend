package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.ProblemTag;

import java.util.Optional;

public class FakeProblemTagRepository implements ProblemTagRepository{

    @Override
    public ProblemTag save(ProblemTag request) {
        return null;
    }

    @Override
    public Optional<ProblemTag> findById(Long id) {
        return Optional.empty();
    }
}
