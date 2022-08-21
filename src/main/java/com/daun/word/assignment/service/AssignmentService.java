package com.daun.word.assignment.service;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.repository.AssignmentRepository;
import com.daun.word.assignment.dto.AssignmentSaveRequest;
import com.daun.word.assignment.dto.AssignmentSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentSaveResponse save(AssignmentSaveRequest request) {
        Assignment assignment = new Assignment();
        assignmentRepository.save(assignment);
        return new AssignmentSaveResponse();
    }

}
