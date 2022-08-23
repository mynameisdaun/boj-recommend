package com.daun.word.assignment.service;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
import com.daun.word.assignment.domain.repository.AssignmentRepository;
import com.daun.word.assignment.dto.AssignmentRequest;
import com.daun.word.assignment.dto.AssignmentResponse;
import com.daun.word.assignment.dto.AssignmentSaveRequest;
import com.daun.word.assignment.dto.AssignmentSaveResponse;
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

    public AssignmentResponse open(Integer detailId) {
        AssignmentDetail detail = assignmentRepository.findDetailByDetailId(detailId)
                .orElseThrow(NoSuchElementException::new);

        logger.info("Before update {}",detail.toString());
        assignmentRepository.open(detail);
        logger.info("After update {}",detail.toString());
        throw new NotImplementedException();
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
