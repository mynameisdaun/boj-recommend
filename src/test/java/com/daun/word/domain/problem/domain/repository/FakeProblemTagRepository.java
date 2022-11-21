package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.ProblemTag;
import com.daun.word.domain.problem.domain.Tag;
import com.daun.word.global.vo.Title;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.daun.word.Fixture.Fixture.problem_16120;

public class FakeProblemTagRepository implements ProblemTagRepository {

    private final Map<Integer, ProblemTag> db = new HashMap<>();

    public FakeProblemTagRepository() {
        Problem problem = problem_16120();
        save(new ProblemTag(problem, new Tag(33, "greedy", new Title("그리디 알고리즘"))));
        save(new ProblemTag(problem, new Tag(71, "stack", new Title("스택"))));
        save(new ProblemTag(problem, new Tag(158, "string", new Title("문자열"))));
        save(new ProblemTag(problem, new Tag(175, "data_structures", new Title("자료 구조"))));
    }

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
