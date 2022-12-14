package com.daun.word.domain.member.service;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.repository.MemberRepository;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.dto.RegisterAuthRequest;
import com.daun.word.domain.member.dto.RegisterRequest;
import com.daun.word.domain.member.exception.DuplicateMemberException;
import com.daun.word.domain.member.exception.IllegalRegisterProcessException;
import com.daun.word.domain.member.exception.NoSuchMemberException;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemQueryRepository;
import com.daun.word.domain.problem.dto.search.ProblemSearchQuery;
import com.daun.word.domain.problem.dto.search.SortDirection;
import com.daun.word.domain.problem.dto.search.SortType;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterService {

    private final MemberRepository memberRepository;
    private final ProblemQueryRepository problemQueryRepository;
    private final SolvedAcClient solvedAcClient;
    private final AssignmentRepository assignmentRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원을 등록한다
     * 인증을 위해서, 풀지 않은 bronze 5 문제를 세가지 선정하고, 그 중 한문제를 풀게한다.
     *
     * @param request RegisterRequest
     * @return Member
     * @throws DuplicateMemberException        같은 이메일로 가입한 회원이 존재할 경우, "이미 가입한 회원입니다."
     * @throws IllegalRegisterProcessException
     */
    @Transactional
    public List<Assignment> register(RegisterRequest request) {
        //중복된 회원인지, 회원가입 과정이 진행중인 유저인지 확인한다
        Member guest = memberRepository.findByEmail(request.getEmail())
                .orElseGet(() -> guest(request));
        memberRepository.save(guest.tempRegister());
        //3000문제 이상 풀린 Level 1 문제 중 가장 쉬운 문제를 선별한다
        List<Problem> filtered = problemQueryRepository.search(new ProblemSearchQuery(1, 1, 3000, SortType.ACCEPTED_USER_COUNT, SortDirection.ASC));
        List<Assignment> assignments = new ArrayList<>();
        for (Problem p : filtered) {
            if (!solvedAcClient.isSolved(Arrays.asList(guest), p)) {
                assignments.add(assignmentRepository.save(new Assignment(UUID.randomUUID(), p, guest)));
            }
            //세 문제만 추출한다.
            if (assignments.size() == 3) {
                return assignments;
            }
        }
        throw new IllegalStateException();
    }

    /**
     * @param request
     * @return
     */
    @Transactional
    public Member authenticate(final RegisterAuthRequest request) {
        final Member unIdentified = findByEmail(request.getEmail());
        final List<Assignment> assignments = assignmentRepository.findByMember(unIdentified);

        for (final Assignment a : assignments) {
            if (solvedAcClient.isSolved(Arrays.asList(unIdentified), a.getProblem())) {
                //해당 회원이 푼 모든 문제를 확인해서 저장한다;
                int page = 1;
                List<Problem> solved = new ArrayList<>();
                ProblemSearchResponse response = solvedAcClient.search("s@" + unIdentified.getEmail().getValue(), page, SortType.ACCEPTED_USER_COUNT, SortDirection.DESC);


                return unIdentified.authenticate();
            }
        }
        throw new IllegalRegisterProcessException("아직 과제를 수행하지 않았습니다.");
    }

    /**
     * @param email
     * @return
     */
    @Transactional(readOnly = true)
    public List<Assignment> auth_assignments(Email email) {
        Member unIdentified = findByEmail(email);
        if (!unIdentified.isTempMember()) {
            throw new IllegalRegisterProcessException("회원가입이 진행중인 회원이 아닙니다.");
        }
        return assignmentRepository.findByMember(unIdentified);
    }

    /**
     * @param request
     * @return
     */
    private Member guest(RegisterRequest request) {
        SolvedAcMember solvedAcMember = solvedAcClient.findMemberByEmail(request.getEmail());
        return new Member(solvedAcMember, request, passwordEncoder);
    }

    /**
     * @param email
     * @return
     */
    private Member findByEmail(Email email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchMemberException("회원이 존재하지 않습니다."));
    }
}
