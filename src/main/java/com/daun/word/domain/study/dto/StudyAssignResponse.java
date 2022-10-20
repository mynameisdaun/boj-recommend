package com.daun.word.domain.study.dto;


import com.daun.word.domain.problem.domain.Problem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyAssignResponse {
    private Problem problem;
}
