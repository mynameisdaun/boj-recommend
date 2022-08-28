package com.daun.word.assignment.dto;

import com.daun.word.assignment.domain.Assignment;

public class AssignmentResponse {
    private final Assignment assignment;

    public AssignmentResponse(Assignment assignment) {
        this.assignment = assignment;
    }
}
