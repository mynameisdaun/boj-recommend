package com.daun.word.problem.service;

import com.daun.word.global.Id;
import com.daun.word.infra.solvedac.SolvedAcClient;
import com.daun.word.problem.domain.Problem;
import com.daun.word.problem.domain.repository.ProblemRepository;
import com.daun.word.problem.domain.vo.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProblemService {
//TODO: Test Class 작성
    private final ProblemRepository problemRepository;

    private final SolvedAcClient solvedAcClient;

    @Transactional(readOnly = true)
    public Problem findById(Id<Problem, Integer> id) {
        return problemRepository.findById(id)
                .orElse(save(solvedAcClient.findById(id)));
    }

    @Transactional
    public Problem save(Problem problem) {
        problem.getTags().forEach(problemRepository::saveTag);
        problemRepository.save(problem);
        problem.getTags().forEach(t-> problemRepository.saveProblemTag(Id.of(Problem.class, problem.getId()), Id.of(Tag.class, t.getId())));
        return problem;
    }
}
