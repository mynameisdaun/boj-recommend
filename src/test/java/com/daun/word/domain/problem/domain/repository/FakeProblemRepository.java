package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeProblemRepository implements ProblemRepository {

    private final Map<Integer, Problem> table = new HashMap<>();

    @Override
    public Problem save(Problem request) {
        table.put(request.getId(), request);
        return request;
    }

    @Override
    public Optional<Problem> findById(Integer id) {
        return Optional.ofNullable(table.get(id));
    }
}
