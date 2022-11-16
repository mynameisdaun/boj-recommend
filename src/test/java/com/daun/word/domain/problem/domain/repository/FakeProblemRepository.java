package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.vo.Tier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Problem> findAllByTierBetweenOrderByAcceptedUserCountDesc(final int min, final int max) {
        return table.values()
                .stream()
                .filter(p -> p.getTier().getLevel() <= max && p.getTier().getLevel() >= min)
                .sorted((a, b) -> b.getAcceptedUserCount() - a.getAcceptedUserCount())
                .collect(Collectors.toList());
    }

    @Override
    public List<Problem> findAllByIdIn(List<Integer> ids) {
        return table.values()
                .stream()
                .filter(p -> ids.contains(p.getId()))
                .collect(Collectors.toList());
    }
}
