package com.daun.word.domain.problem.dto;

import com.daun.word.domain.problem.domain.Problem;
import lombok.Data;

@Data
public class ProblemDTO {

    Problem problem;

    public ProblemDTO(Problem problem) {
        this.problem=problem;
    }
}
