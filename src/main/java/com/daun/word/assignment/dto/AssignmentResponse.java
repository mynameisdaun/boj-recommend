package com.daun.word.assignment.dto;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
import lombok.RequiredArgsConstructor;

public class AssignmentResponse {
    private final Assignment assignment;

    public AssignmentResponse(Assignment assignment) {
        this.assignment=assignment;
    }
}
