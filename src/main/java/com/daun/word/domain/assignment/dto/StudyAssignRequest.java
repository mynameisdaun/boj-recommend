package com.daun.word.domain.assignment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StudyAssignRequest {
    @NotNull
    private UUID studyId;
    @NotNull
    private String hash;
    @NotNull
    private Integer problemId;
}
