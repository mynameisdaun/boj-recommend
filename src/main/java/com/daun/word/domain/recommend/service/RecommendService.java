package com.daun.word.domain.recommend.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.recommend.domain.repository.RecommendRepository;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.global.Id;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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

    @Transactional(readOnly = true)
    public Recommend findById(Id<Recommend, Integer> id) {
        return recommendRepository.findById(id).
                orElseThrow(NoSuchElementException::new);
    }



    //TODO: 환경변수로 정리하는 것도 좋은 방법이겠다.
    @Transactional
    public List<Recommend> recommendForMember(Member member) {
        //-3 +1
        int offset = 0;
        int limit = 50;
        final int minRecommendPoolSize = 15;
        final int recommendSize = 2;
        List<Problem> recommendPool = new ArrayList<>();

        while (true) {
            List<Problem> problems = problemRepository.findByTierBetweenOrderBySolvedCountDesc(member.getTier().minus(3), member.getTier().plus(1), offset, limit);
            if (problems.isEmpty()) break;

            List<Problem> unsolved = solvedAcClient.unSolvedProblemsByMember(member, problems);
            recommendPool.addAll(unsolved);

            if (unsolved.isEmpty() || recommendPool.size() < minRecommendPoolSize) {
                offset++;
                continue;
            }
            Collections.shuffle(recommendPool);
            List<Recommend> recommends = recommendPool.stream()
                    .limit(recommendSize)
                    .map(a -> new Recommend(a, member))
                    .collect(Collectors.toList());
            recommends.forEach(this::save);
            return recommends;
        }
        throw new NoSuchElementException("모든 문제를 다 검색했습니다");
    }

    @Transactional
    public Recommend save(Recommend recommend) {
        recommendRepository.save(recommend);
        return recommend;
    }

}
