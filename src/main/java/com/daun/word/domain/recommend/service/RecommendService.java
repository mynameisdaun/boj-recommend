package com.daun.word.domain.recommend.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.recommend.domain.repository.RecommendRepository;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendService {

    private final RecommendRepository recommendRepository;

    private final ProblemRepository problemRepository;

    private final SolvedAcClient solvedAcClient;

    @Transactional
    public Recommend save(Recommend recommend) {
        recommendRepository.save(recommend);
        return recommend;
    }

    @Transactional
    public Recommend choose(Member member, Problem problem) {
        Recommend recommend = recommendRepository.findByMemberAndProblem(member, problem)
                .orElseThrow(NoSuchElementException::new);
        recommend.choose();
        return save(recommend);
    }

    @Transactional
    public List<Recommend> recommendForMember(Member member) {
        //-3 +1
        int offset = 0;
        int limit = 50;
        while (true) {
            List<Problem> problems = problemRepository.findByTierBetweenOrderBySolvedCountDesc(member.getTier().minus(3), member.getTier().plus(1), offset, limit);
            log.info("search problems: {}", problems);
            if (problems.isEmpty()) break;
            List<Problem> unsolved = solvedAcClient.unSolvedProblemsByMember(member, problems);
            log.info("solved: {}", unsolved);
            if (unsolved.isEmpty()) {
                offset++;
                continue;
            }
            List<Recommend> recommends = unsolved.stream()
                    .limit(3).map(a -> new Recommend(a, member)).collect(Collectors.toList());
            recommends.forEach(this::save);
            return recommends;
        }
        throw new NoSuchElementException("모든 문제를 다 검색했습니다");
    }
}
