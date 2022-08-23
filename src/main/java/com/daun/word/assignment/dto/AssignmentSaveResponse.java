package com.daun.word.assignment.dto;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
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
