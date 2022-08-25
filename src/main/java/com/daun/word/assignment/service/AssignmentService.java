package com.daun.word.assignment.service;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
import com.daun.word.assignment.domain.repository.AssignmentRepository;
import com.daun.word.assignment.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class AssignmentService {
    private Logger logger = LoggerFactory.getLogger(AssignmentService.class);

    private final AssignmentRepository assignmentRepository;

    public AssignmentDetailResponse open(Integer detailId) {
        AssignmentDetail detail = assignmentRepository.findDetailByDetailId(detailId)
                .orElseThrow(NoSuchElementException::new);
        detail.open();
        assignmentRepository.updateDetail(detail);
        return new AssignmentDetailResponse(detail);
    };

    public AssignmentResponse complete(AssignmentRequest request) {
        throw new NotImplementedException();
    };

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
