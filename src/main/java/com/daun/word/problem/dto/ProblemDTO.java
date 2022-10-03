package com.daun.word.problem.dto;

import com.daun.word.problem.domain.Problem;
import lombok.Data;

@Data
public class ProblemDTO {

    Problem problem;

    public ProblemDTO(Problem problem) {
        this.problem=problem;
    }
}
