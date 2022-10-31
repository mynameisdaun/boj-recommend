package com.daun.word.domain.recommend.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.recommend.domain.repository.RecommendRepository;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import com.daun.word.global.GlobalId;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.vo.Tier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendService {

    private final RecommendRepository recommendRepository;

    private final ProblemRepository problemRepository;

    private final SolvedAcClient solvedAcClient;

//    @Transactional
//    public Recommend assign(Study study, Problem problem) {
//        List<Problem> problems = solvedAcClient.unSolvedProblemsByMembers(
//                study.getStudyMembers().stream().map(StudyMember::getMember).collect(toList()), Arrays.asList(problem));
//        if (problems.isEmpty()) {
//            throw new IllegalArgumentException();
//        }
//        return new Recommend(UUID.randomUUID(), problems.get(0), null);
//    }
//
//    @Transactional(readOnly = true)
//    public Recommend findById(GlobalId<Recommend, Integer> globalId) {
//        return recommendRepository.findById(globalId).
//                orElseThrow(NoSuchElementException::new);
//    }
//
//
//    @Transactional
//    public List<Recommend> recommendForStudy(Study study) throws IOException {
//        List<Member> members = study.getStudyMembers().stream().map(StudyMember::getMember).collect(toList());
//        List<Tier> tiers = members.stream().map(Member::getTier).sorted().collect(toList());
//        List<Problem> recommendPool = new ArrayList<>();
//
//        final int minRecommendPoolSize = 5;
//        final int recommendSize = 1;
//        if (tiers.size() > 2) {
//            tiers.remove(0);
//            tiers.remove(tiers.size() - 1);
//        }
//        Tier minTier = tiers.get(0);
//        Tier maxTier = tiers.get(tiers.size() - 1);
//        int offset = 0;
//        int limit = 50;
//
//
//        final List<GlobalId<Problem, Integer>> recommendedIds = recommendRepository.findByMembersWhereCreatedBefore(members, LocalDateTime.now().minusDays(7))
//                .stream()
//                .map(r -> GlobalId.of(Problem.class, r.getProblem().getId()))
//                .distinct()
//                .collect(toList());
//        while (true) {
//            List<Problem> problems = problemRepository.findByTierBetweenOrderBySolvedCountDesc(minTier, maxTier, offset, limit);
//            if (problems.isEmpty()) {
//                throw new NoSuchElementException("모든 문제를 다 검색했습니다");
//            }
//            List<Problem> unsolved = solvedAcClient.unSolvedProblemsByMembers(members, problems);
//            log.info("안푼문제: {}", unsolved);
//            recommendPool.addAll(unsolved.stream().filter(p -> !recommendedIds.contains(GlobalId.of(Problem.class, p.getId()))).collect(toList()));
//            if (unsolved.isEmpty() || recommendPool.size() < minRecommendPoolSize) {
//                offset += limit;
//                continue;
//            }
//            break;
//        }
//        Collections.shuffle(recommendPool);
//        List<Recommend> recommends = new ArrayList<>();
//        for (Member m : members) {
//            for (int i = 0; i < recommendSize; i++) {
//                Recommend r = new Recommend(UUID.randomUUID(), recommendPool.get(i), m);
//                recommendRepository.save(r);
//                recommends.add(r);
//            }
//        }
//        return recommends;
//    }
//
//    public List<Recommend> recommendForMember_v2(Member member) {
//        List<Member> members = Arrays.asList(member);
//        List<Tier> tiers = members.stream().map(Member::getTier).sorted().collect(toList());
//        List<Problem> recommendPool = new ArrayList<>();
//
//        final int minRecommendPoolSize = 5;
//        final int recommendSize = 1;
//        if (tiers.size() > 2) {
//            tiers.remove(0);
//            tiers.remove(tiers.size() - 1);
//        }
//        Tier minTier = tiers.get(0);
//        Tier maxTier = tiers.get(tiers.size() - 1);
//        int offset = 0;
//        int limit = 50;
//
//
//        final List<GlobalId<Problem, Integer>> recommendedIds = recommendRepository.findByMembersWhereCreatedBefore(members, LocalDateTime.now().minusDays(7))
//                .stream()
//                .map(r -> GlobalId.of(Problem.class, r.getProblem().getId()))
//                .distinct()
//                .collect(toList());
//        while (true) {
//            List<Problem> problems = problemRepository.findByTierBetweenOrderBySolvedCountDesc(minTier, maxTier, offset, limit);
//            if (problems.isEmpty()) {
//                throw new NoSuchElementException("모든 문제를 다 검색했습니다");
//            }
//            List<Problem> unsolved = solvedAcClient.unSolvedProblemsByMembers(members, problems);
//            log.info("안푼문제: {}", unsolved);
//            recommendPool.addAll(unsolved.stream().filter(p -> !recommendedIds.contains(GlobalId.of(Problem.class, p.getId()))).collect(toList()));
//            if (unsolved.isEmpty() || recommendPool.size() < minRecommendPoolSize) {
//                offset += limit;
//                continue;
//            }
//            break;
//        }
//        Collections.shuffle(recommendPool);
//        List<Recommend> recommends = new ArrayList<>();
//        for (Member m : members) {
//            for (int i = 0; i < recommendSize; i++) {
//                Recommend r = new Recommend(UUID.randomUUID(), recommendPool.get(i), m);
//                recommendRepository.save(r);
//                recommends.add(r);
//            }
//        }
//        return recommends;
//    }
//
//
//    //TODO: 환경변수로 정리하는 것도 좋은 방법이겠다.
//    @Transactional
//    public List<Recommend> recommendForMember(Member member) {
//        //-3 +1
//        int offset = 0;
//        int limit = 50;
//        final int minRecommendPoolSize = 10;
//        final int recommendSize = 1;
//        List<Problem> recommendPool = new ArrayList<>();
//
//        final List<GlobalId<Problem, Integer>> recommendedIds = recommendRepository.findByMembersWhereCreatedBefore(Arrays.asList(member), LocalDateTime.now().minusDays(7))
//                .stream()
//                .map(r -> GlobalId.of(Problem.class, r.getProblem().getId()))
//                .distinct()
//                .collect(toList());
//
//        while (true) {
//            List<Problem> problems = problemRepository.findByTierBetweenOrderBySolvedCountDesc(member.getTier().minus(3), member.getTier().plus(1), offset, limit);
//            if (problems.isEmpty()) break;
//
//            List<Problem> unsolved = solvedAcClient.unSolvedProblemsByMember(member, problems);
//            recommendPool.addAll(unsolved.stream().filter(p -> !recommendedIds.contains(GlobalId.of(Problem.class, p.getId()))).collect(toList()));
//            if (unsolved.isEmpty() || recommendPool.size() < minRecommendPoolSize) {
//                offset += limit;
//                continue;
//            }
//            Collections.shuffle(recommendPool);
//            List<Recommend> recommends = recommendPool.stream()
//                    .limit(recommendSize)
//                    .map(a -> new Recommend(UUID.randomUUID(), a, member))
//                    .collect(toList());
//            recommends.forEach(this::save);
//            return recommends;
//        }
//        throw new NoSuchElementException("모든 문제를 다 검색했습니다");
//    }
//
//    @Transactional
//    public Recommend save(Recommend recommend) {
//        recommendRepository.save(recommend);
//        return recommend;
//    }

}
