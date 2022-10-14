package com.daun.word.domain.recommend.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.recommend.domain.repository.RecommendRepository;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.global.Id;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.vo.Tier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

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

    @Transactional
    public List<Recommend> recommendForStudy(Study study) throws IOException {
        List<Member> members = study.getMembers();
        List<Tier> tiers = members.stream().map(Member::getTier).sorted().collect(toList());
        List<Problem> recommendPool = new ArrayList<>();

        final int minRecommendPoolSize = 5;
        final int recommendSize = 1;
        if (tiers.size() > 2) {
            tiers.remove(0);
            tiers.remove(tiers.size() - 1);
        }
        Tier minTier = tiers.get(0);
        Tier maxTier = tiers.get(tiers.size() - 1);
        int offset = 0;
        int limit = 50;

        //일주일 이내에 어떤 멤버에게라도 추천한 문제가 있다면, 재 추천하지 않는다.
        final List<Id<Problem, Integer>> recommendedIds = recommendRepository.findByMembersWhereCreatedBefore(members, LocalDateTime.now().minusDays(7))
                .stream()
                .map(r -> Id.of(Problem.class, r.getProblem().getId()))
                .distinct()
                .collect(toList());
        while (true) {
            List<Problem> problems = problemRepository.findByTierBetweenOrderBySolvedCountDesc(minTier, maxTier, offset, limit);
            if (problems.isEmpty()) {
                throw new NoSuchElementException("모든 문제를 다 검색했습니다");
            }
            List<Problem> unsolved = solvedAcClient.unSolvedProblemsByMembers(members, problems);
            log.info("안푼문제: {}",unsolved);
            recommendPool.addAll(unsolved.stream().filter(p -> !recommendedIds.contains(Id.of(Problem.class, p.getId()))).collect(toList()));
            if (unsolved.isEmpty() || recommendPool.size() < minRecommendPoolSize) {
                offset++;
                continue;
            }
            break;
        }
        Collections.shuffle(recommendPool);
        List<Recommend> recommends = new ArrayList<>();
        for (Member m : members) {
            for (int i = 0; i < recommendSize; i++) {
                Recommend r = new Recommend(recommendPool.get(i), m);
                recommendRepository.save(r);
                recommends.add(r);
            }
        }
        return recommends;
    }


    //TODO: 환경변수로 정리하는 것도 좋은 방법이겠다.
    @Transactional
    public List<Recommend> recommendForMember(Member member) {
        //-3 +1
        int offset = 0;
        int limit = 50;
        final int minRecommendPoolSize = 10;
        final int recommendSize = 1;
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
                    .collect(toList());
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
