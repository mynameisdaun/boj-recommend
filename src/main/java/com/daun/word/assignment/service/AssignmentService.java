package com.daun.word.assignment.service;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
import com.daun.word.assignment.domain.repository.AssignmentRepository;
import com.daun.word.assignment.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final AssignmentRepository assignmentRepository;

    /* 과제를 열람한다 */
    public AssignmentDetailResponse open(Integer detailId) {
        AssignmentDetail detail = assignmentRepository.findDetailByDetailId(detailId)
                .orElseThrow(NoSuchElementException::new);
        detail.open();
        assignmentRepository.updateDetail(detail);
        return new AssignmentDetailResponse(detail);
    }

    /* 과제를 제출한다 */
    public AssignmentDetailResponse submission(SubmissionRequest request) {
        AssignmentDetail detail = assignmentRepository.findDetailByDetailId(request.getId())
                .orElseThrow(NoSuchElementException::new);
        detail.submission(request.getSubmission());
        assignmentRepository.updateDetail(detail);
        return new AssignmentDetailResponse(detail);
    }

    /* 과제를 등록한다 */
    public AssignmentSaveResponse save(AssignmentSaveRequest request) {
        Assignment assignment = Assignment.fromSaveRequest(request);
        assignmentRepository.save(assignment);
        List<AssignmentDetail> details = new ArrayList<>();
        request.getDetails().forEach(req -> {
            AssignmentDetail detail = AssignmentDetail.fromSaveRequest(assignment.getId(), req);
            assignmentRepository.saveDetail(detail);
            details.add(detail);
        });
        return new AssignmentSaveResponse(assignment, details);
    }

    /* 과제 id로 과제를 찾는다 */
    public AssignmentResponse findById(AssignmentRequest request) {
        return new AssignmentResponse(
                assignmentRepository.findAssignmentById(request.getAssignmentId())
                        .orElseThrow(NoSuchElementException::new)
        );
    }
}
