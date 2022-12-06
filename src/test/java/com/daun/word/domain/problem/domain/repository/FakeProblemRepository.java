package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.vo.Tier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.daun.word.Fixture.Fixture.*;

public class FakeProblemRepository implements ProblemRepository {

    private final Map<Integer, Problem> table = new HashMap<>();

    public FakeProblemRepository() {
        save(problem_16120());
        save(problem_1002());
        save(problem_19());
        save(problem_29());
        save(problem_39());
    }

    @Override
    public Problem save(Problem request) {
        table.put(request.getId(), request);
        return request;
    }

    @Override
    public Optional<Problem> findById(Integer id) {
        return Optional.ofNullable(table.get(id));
    }

    @Override
    public List<Problem> findAllByIdIn(List<Integer> ids) {
        return table.values()
                .stream()
                .filter(p -> ids.contains(p.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public int countByTier(Tier tier) {
        return (int) table.values().stream().filter(p -> p.getTier().equals(tier)).count();
    }
}
