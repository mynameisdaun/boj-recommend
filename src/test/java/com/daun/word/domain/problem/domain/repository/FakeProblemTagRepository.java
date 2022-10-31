package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.ProblemTag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FakeProblemTagRepository implements ProblemTagRepository {

    Map<Integer, ProblemTag> db = new HashMap<>();

    @Override
    public ProblemTag save(ProblemTag request) {
        db.put(request.getId(), request);
        return request;
    }

    @Override
    public Optional<ProblemTag> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<ProblemTag> findProblemTagsByProblem(Problem problem) {
        return db.values()
                .stream()
                .filter(pt -> pt.getProblem().equals(problem))
                .collect(Collectors.toList());
    }
}
