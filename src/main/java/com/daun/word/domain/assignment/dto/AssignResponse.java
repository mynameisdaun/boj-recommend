package com.daun.word.domain.assignment.dto;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.problem.dto.ProblemDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class AssignResponse {

    private final String id;
    private final String email;
    private final ProblemDTO problem;

    public AssignResponse(Assignment assignment) {
        this.id = assignment.getId().toString();
        this.email = assignment.getMember().getEmail().getValue();
        this.problem = new ProblemDTO(assignment.getProblem());
    }
}
