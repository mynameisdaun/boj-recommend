package com.daun.word.domain.assignment.dto;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.AssignmentDetail;
import lombok.Data;

import java.util.List;

@Data
public class AssignmentSaveResponse {
    Assignment assignment;
    List<AssignmentDetail> details;

    public AssignmentSaveResponse(Assignment assignment, List<AssignmentDetail> details) {
        this.assignment = assignment;
        this.details = details;
    }
}
