package com.daun.word.domain.problem.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.problem.domain.vo.Tag;
import com.daun.word.global.Id;
import com.daun.word.global.constant.Constants;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.vo.Tier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.daun.word.global.constant.Constants.searchSize;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    private final SolvedAcClient solvedAcClient;

    @Transactional
    public List<Problem> recommend(Member member) {
        Tier tier = solvedAcClient.findMemberTier(member);
        int offset = 0;
        List<Problem> recommended = new ArrayList<>();
        while (true && offset < 3000) {
            List<Problem> problems = problemRepository.findByTierBetweenOrderBySolvedCountDesc(tier.minus(-4), tier.plus(1), offset, searchSize);
            List<Problem> solved = solvedAcClient.checkProblemsSolved(member, problems.stream()
                    .map(p -> Id.of(Problem.class, p.getId()))
                    .collect(Collectors.toList()));
            if (solved.isEmpty()) {
                offset+=searchSize;
                continue;
            }
            recommended.addAll(problems.stream().filter(p -> !solved.contains(p))
                    .collect(Collectors.toList()));
            if (recommended.size() < 3) {
                offset+=searchSize;
                continue;
            }
            break;
        }
        if (recommended.isEmpty()) throw new IllegalStateException("추천 문제를 찾을 수 없습니다.");
        return recommended.stream()
                .limit(Constants.recommendProblemSize)
                .collect(Collectors.toList());
    }

    @Transactional
    public Problem findById(Id<Problem, Integer> id) {
        return problemRepository.findById(id)
                .orElse(save(solvedAcClient.findById(id)));
    }

    @Transactional
    public Problem save(Problem problem) {
        problem.getTags().forEach(problemRepository::saveTag);
        problemRepository.save(problem);
        problem.getTags().forEach(t -> problemRepository.saveProblemTag(Id.of(Problem.class, problem.getId()), Id.of(Tag.class, t.getId())));
        return problem;
    }
}
