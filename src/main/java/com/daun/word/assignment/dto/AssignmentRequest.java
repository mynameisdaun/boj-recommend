package com.daun.word.assignment.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AssignmentRequest {
    @NotNull
    @Positive
    private Integer assignmentId;
}
