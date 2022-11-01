package com.daun.word.domain.assignment.service;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.assignment.dto.AssignRequest;
import com.daun.word.domain.assignment.dto.StudyAssignRequest;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.service.ProblemService;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import com.daun.word.domain.study.service.StudyHashService;
import com.daun.word.domain.study.service.StudyService;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final MemberService memberService;

    private final ProblemService problemService;

    private final SolvedAcClient solvedAcClient;

    private final StudyService studyService;

    private final StudyHashService hashService;

    /**
     * 스터디에 포함되어 있는 전원에게 과제를 등록한다
     * <p>
     * 스터디원 중 한 명이라도 이미 과제를 할당 받았거나, 풀었던적이 있다면, 모두에게 과제 할당을 할 수 없으며
     * 요청이 취소된다
     *
     * @param request
     * @return List Assignment
     * @throws AuthenticationException hashKey가 올바르지 않으면 "올바른 스터디 키가 아닙니다"
     */
    @Transactional
    public List<Assignment> assignForStudy(final StudyAssignRequest request) throws AuthenticationException {

        Study study = studyService.findById(request.getStudyId());
        study.auth(request.getHash(), hashService);
        Problem problem = problemService.findById(request.getProblemId());
        List<Assignment> assignments = new ArrayList<>();

        for (StudyMember m : study.getStudyMembers()) {
            Email assignTo = m.getMember().getEmail();
            Assignment assignment = assign(new AssignRequest(assignTo, problem.getId()));
            assignments.add(assignment);
        }
        return assignments;
    }


    /**
     * 과제를 등록한다
     *
     * @param request AssignmentSaveRequest
     * @return Assignment
     * @throws IllegalArgumentException 요청이 Null일 경우 "올바르지 않은 과제 생성 요청입니다"
     * @throws IllegalStateException    과제 할당 내역이 있고 && 1. assignment.complete, 2. solvedAc에서 회원이 푼 기록 있다면
     *                                  "이미 완료된 과제입니다"
     * @throws IllegalStateException    과제 할당 내역은 있으나, 문제를 푼 기록이 없다면 "이미 할당된 과제입니다"
     */
    @Transactional
    public Assignment assign(AssignRequest request) {
        checkArgument(request != null, "올바르지 않은 과제 생성 요청입니다");
        Member member = memberService.findByEmail(request.getAssignTo());
        Problem problem = problemService.findById(request.getProblemId());

        Optional<Assignment> maybeAssignment = assignmentRepository.findByMemberAndProblem(member, problem);
        if (maybeAssignment.isPresent()) {
            Assignment assignment = maybeAssignment.get();
            boolean complete = assignment.isComplete();
            if (!complete && solvedAcClient.isSolved(member, problem)) {
                assignment.complete();
                assignmentRepository.save(assignment);
                complete = true;
            }
            if (complete) {
                throw new IllegalStateException("이미 완료된 과제입니다");
            }
            throw new IllegalStateException("이미 할당된 과제입니다");
        }

        Assignment assignment = new Assignment(UUID.randomUUID(), member, problem);
        assignmentRepository.save(assignment);
        return assignment;
    }

    /**
     * 과제를 조회한다
     *
     * @param id UUID
     * @return Assignment
     * @throws NoSuchElementException 과제가 존재하지 않을 경우 "존재하지 않는 과제 입니다"
     */
    @Transactional(readOnly = true)
    public Assignment findById(final UUID id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 과제 입니다"));
    }

    /**
     * 모든 과제를 조회한다
     *
     * @return List Assignment
     */
    @Transactional(readOnly = true)
    public List<Assignment> findAll() {
        return assignmentRepository.findAllByDeleted(false);
    }
}
