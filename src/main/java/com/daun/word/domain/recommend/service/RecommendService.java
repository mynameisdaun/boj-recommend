package com.daun.word.domain.recommend.service;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemQueryRepository;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.recommend.domain.repository.RecommendRepository;
import com.daun.word.domain.recommend.dto.RecommendRequest;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendService {

    private final RecommendRepository recommendRepository;

    private final MemberService memberService;

    private final SolvedAcClient solvedAcClient;

    private final ProblemQueryRepository problemQueryRepository;

    private final AssignmentRepository assignmentRepository;


    /**
     * id로 추천을 조회한다
     *
     * @param id 문제추천 id
     * @return Recommend
     * @throws NoSuchElementException 존재하지 않는 추천입니다
     */
    @Transactional(readOnly = true)
    public Recommend findById(UUID id) {
        return recommendRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 추천입니다"));
    }

    /**
     * 문제를 추천한다
     * 1. 문제 추천 타입에 따라서 문제 풀을 생성하고,
     * 2. 문제 풀에서 이미 과제로 할당된 적이 있는 문제는 필터링한다
     * 3. 추천을 확정하기 전, 로컬에는 기록이 없지만, BOJ에서 문제를 푼 기록이 있는지 다시 확인한다
     * 4-1. BOJ에서 푼 기록이 없다면, 문제를 추천하고, 과제를 등록한다
     * 4-2. BOJ에서 푼 기록이 있다면, 푼 기록을 로컬에 저장하고 추천 문제 검색을 이어간다
     *
     * @param request
     * @return Recommend
     * @throws IllegalStateException 현재 문제 풀 생성 전략으로 더 이상 추천 할 수 있는 문제가 없는 경우 "추천할 수 있는 문제가 없습니다"
     */
    @Transactional
    public List<Recommend> recommend(RecommendRequest request) {
        final List<Member> members = memberService.findByEmailIn(request.emails());

        //1. 문제 추천 전략에 따라서, 문제 풀 생성
        final List<Problem> problemPools = problemQueryRepository.search(request.getQuery());
        System.out.println("problemPools: ");
        for (Problem p : problemPools) {
            System.out.println(p);
        }

        //2. 이미 할당 받은 문제는 필터링
        final List<Problem> assigned = assignmentRepository.findAllByMembersAndProblemIn(members, problemPools).stream().map(Assignment::getProblem).collect(toList());
        System.out.println("assigned: ");
        for (Problem p : assigned) {
            System.out.println(p);
        }

        final List<Problem> filtered = problemPools.stream().filter(p -> !assigned.contains(p)).collect(toList());

        System.out.println("filtered: ");
        for (Problem p : filtered) {
            System.out.println(p);
        }
        //3. BOJ에서 재 검증
        for (Problem p : filtered) {
            if (!solvedAcClient.isSolved(members, p)) {
                List<Recommend> response = new ArrayList<>();
                for (Member m : members) {
                    assignmentRepository.save(new Assignment(UUID.randomUUID(), p, m).complete());
                    response.add(recommendRepository.save(new Recommend(UUID.randomUUID(), p, m, request.getType())));
                }
                return response;
            }
        }
        throw new IllegalStateException("추천할 수 있는 문제가 없습니다");
    }

}
