package com.daun.word.assignment.dto;

import com.daun.word.assignment.domain.AssignmentDetail;
import lombok.Getter;

@Getter
public class AssignmentDetailResponse {
    private final AssignmentDetail detail;

    public AssignmentDetailResponse(AssignmentDetail detail) {
        this.detail = detail;
    }
}
