package com.daun.word.domain.assignment.service;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.AssignmentDetail;
import com.daun.word.domain.assignment.domain.PAssignment;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.assignment.domain.repository.PAssignmentRepository;
import com.daun.word.domain.assignment.dto.*;
import com.daun.word.global.Id;
import com.daun.word.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class AssignmentService {
    private final AssignmentRepository deprecated;

    private final PAssignmentRepository assignmentRepository;

    private final ProblemService problemService;

    public PAssignment findById(Id<PAssignment, Integer> id) {
        return assignmentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public PAssignment save(AssignmentSaveRequest request) {
        PAssignment assignment = new PAssignment(problemService.findById(request.getProblemId()), request.getAssignFrom(), request.getAssignTo(), request.getStartDateTime(), request.getEndDateTime());
        assignmentRepository.save(assignment);
        return assignment;
    }

    //TODO: update 관리
    public PAssignment update(AssignmentSaveRequest request) {
        throw new NotImplementedException("아직구현안했다!");
    }

    /* deprecated */
    public AssignmentDetailResponse open_d(Integer detailId) {
        AssignmentDetail detail = deprecated.findDetailByDetailId(detailId)
                .orElseThrow(NoSuchElementException::new);
        detail.open();
        deprecated.updateDetail(detail);
        return new AssignmentDetailResponse(detail);
    }

    /* deprecated */
    public AssignmentDetailResponse submission_d(SubmissionRequest request) {
        AssignmentDetail detail = deprecated.findDetailByDetailId(request.getId())
                .orElseThrow(NoSuchElementException::new);
        detail.submission(request.getSubmission());
        deprecated.updateDetail(detail);
        return new AssignmentDetailResponse(detail);
    }

    /* deprecated */
    public AssignmentSaveResponse save_d(d_AssignmentSaveRequest request) {
        Assignment assignment = Assignment.fromSaveRequest(request);
        deprecated.saved(assignment);
        List<AssignmentDetail> details = new ArrayList<>();
        request.getDetails().forEach(req -> {
            AssignmentDetail detail = AssignmentDetail.fromSaveRequest(assignment.getId(), req);
            deprecated.saveDetail(detail);
            details.add(detail);
        });
        return new AssignmentSaveResponse(assignment, details);
    }

    /* deprecated */
    public AssignmentResponse findById_d(AssignmentRequest request) {
        return new AssignmentResponse(
                deprecated.findAssignmentById(request.getAssignmentId())
                        .orElseThrow(NoSuchElementException::new)
        );
    }
}
