package com.daun.word.domain.reference.dto;

import com.daun.word.domain.problem.dto.ProblemDTO;
import com.daun.word.domain.reference.domain.Reference;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RefRegisterResponse {

    private boolean allowed;

    private String resource;

    private String type;

    private List<ProblemDTO> problems;

    public RefRegisterResponse(final List<Reference> references) {
        if (references == null || references.isEmpty()) {
            throw new IllegalStateException();
        }
        this.allowed = references.get(0).isAllowed();
        this.resource = references.get(0).getResource().getValue();
        this.type = references.get(0).getType().name();
        this.problems = references.stream()
                .map(r -> new ProblemDTO(r.getProblem()))
                .collect(Collectors.toList());
    }
}
