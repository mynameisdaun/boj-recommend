package com.daun.word.domain.member.service;

import com.daun.word.config.security.JwtAuthentication;
import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.problem.dto.search.SortDirection;
import com.daun.word.domain.problem.dto.search.SortType;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateMemberInfoService {

    private final SolvedAcClient solvedAcClient;

    private final AssignmentRepository assignmentRepository;

    private final ProblemRepository problemRepository;

    private final MemberService memberService;

    /**
     * @param member
     * @return
     */
    public Member solved(final JwtAuthentication request) {
        Member member = memberService.findByEmail(request.getEmail());
        final int localCount = assignmentRepository.countByMember(member);
        log.info("localCount: {}", localCount);
        SolvedAcMember solvedAcMember = solvedAcClient.findMemberByEmail(member.getEmail());
        if (solvedAcMember.getSolvedCount() == localCount) {
            return member;
        }
        List<Integer> remote = getRemoteSolvedIds(member);
        log.info("remote: {}\n{}", remote.size(), remote);
        Set<Integer> local = getLocalSolvedIds(member);
        log.info("local: {}\n{}", local.size(), local);
        List<Integer> filtered = remote.stream().filter(r -> !local.contains(r)).collect(toList());
        log.info("filtered: {}\n{}", filtered.size(), filtered);
        for (Problem p : problemRepository.findAllByIdIn(filtered)) {
            assignmentRepository.save(new Assignment(UUID.randomUUID(), p, member).complete());
        }
        return member;
    }


    private List<Integer> getRemoteSolvedIds(Member member) {
        List<Integer> remote = new ArrayList<>();
        int page = 1;
        ProblemSearchResponse response = solvedAcClient.search("s@" + member.getEmail().getValue(), page, SortType.ACCEPTED_USER_COUNT, SortDirection.ASC);
        remote.addAll(response.toIdList());
        log.info("totalCount : {}", response.getCount());
        final int totalPage = (int) Math.ceil(response.getCount() / 50.00);
        log.info("totalPage : {}", totalPage);
        for (int i = page + 1; i <= totalPage; i++) {
            log.info("currentPage : {}", i);
            remote.addAll(solvedAcClient.search("s@" + member.getEmail().getValue(), i, SortType.ACCEPTED_USER_COUNT, SortDirection.ASC).toIdList());
        }
        return remote;
    }

    private Set<Integer> getLocalSolvedIds(Member member) {
        return assignmentRepository.findByMember(member)
                .stream()
                .map(a -> a.getProblem().getId())
                .collect(toSet());
    }

}
