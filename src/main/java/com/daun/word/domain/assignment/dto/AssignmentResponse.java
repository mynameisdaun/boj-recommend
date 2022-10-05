package com.daun.word.domain.assignment.dto;

import com.daun.word.domain.assignment.domain.Assignment;

public class AssignmentResponse {
    private final Assignment assignment;

    public AssignmentResponse(Assignment assignment) {
        this.assignment = assignment;
    }
}
