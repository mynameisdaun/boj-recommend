package com.daun.word.domain.assignment.service;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.assignment.dto.AssignmentSaveRequest;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final MemberService memberService;

    private final ProblemService problemService;

    /**
     * 과제를 등록한다
     *
     * @param request AssignmentSaveRequest
     * @return Assignment
     * @throws IllegalArgumentException 요청이 Null일 경우 "올바르지 않은 과제 생성 요청입니다"
     */
    @Transactional
    public Assignment save(AssignmentSaveRequest request) {
        checkArgument(request != null, "올바르지 않은 과제 생성 요청입니다");
        Member member = memberService.findByEmail(request.getAssignTo());
        Problem problem = problemService.findById(request.getProblemId());
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
}
