package com.daun.word.domain.study.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyAssignRequest {
    private UUID id;
    private String key;
    private Integer problemId;
}
