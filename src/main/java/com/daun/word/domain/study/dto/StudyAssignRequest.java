package com.daun.word.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class StudyAssignRequest {

    private Integer id;
    private String key;

}
