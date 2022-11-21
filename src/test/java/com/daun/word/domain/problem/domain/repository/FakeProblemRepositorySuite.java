package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.ProblemTag;

import java.util.List;
import java.util.Optional;

import static com.daun.word.Fixture.Fixture.problem_16120;

/**
 * 가짜 객체에서의 영속성 전이를 위해 사용한다
 */
public class FakeProblemRepositorySuite extends FakeProblemRepository {

    private final ProblemTagRepository problemTagRepository;

    public FakeProblemRepositorySuite(ProblemTagRepository problemTagRepository) {
        super();
        this.problemTagRepository = problemTagRepository;
        save(problem_16120());
    }

    @Override
    public Optional<Problem> findById(Integer id) {
        Optional<Problem> maybeProblem = super.findById(id);
        if (!maybeProblem.isPresent()) return Optional.empty();
        Problem problem = maybeProblem.get();
        List<ProblemTag> problemTagsByProblem = problemTagRepository.findProblemTagsByProblem(problem);
        problem.addTags(problemTagsByProblem);
        return Optional.ofNullable(problem);
    }
}
