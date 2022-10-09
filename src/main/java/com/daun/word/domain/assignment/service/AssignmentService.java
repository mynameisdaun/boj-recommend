package com.daun.word.domain.assignment.service;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.assignment.dto.AssignmentSaveRequest;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.service.ProblemService;
import com.daun.word.global.Id;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentService {

    private final MemberService memberService;

    private final AssignmentRepository assignmentRepository;

    private final ProblemService problemService;

    private final SolvedAcClient solvedAcClient;

    @Transactional
    public Assignment findById(Id<Assignment, Integer> id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Member assignTo = memberService.findByEmail(assignment.getAssignTo());
        if (!assignment.isComplete()) {
            List<Problem> unsolved = solvedAcClient.unSolvedProblemsByMember(assignTo, Arrays.asList(assignment.getProblem()));
            if (unsolved.isEmpty()) {
                assignment.complete();
                assignmentRepository.save(assignment);
            }
        }
        return assignment;
    }

    @Transactional
    public Assignment save(AssignmentSaveRequest request) {
        Assignment assignment = new Assignment(problemService.findById(request.getProblemId()), request.getAssignFrom(), request.getAssignTo(), request.getStartDateTime(), request.getEndDateTime());
        assignmentRepository.save(assignment);
        return assignment;
    }
}
