package com.daun.word.domain.reference.dto;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.reference.domain.Reference;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReferenceDTO {

    private Problem problem;
    private String resource;
    private String type;
    private boolean positive;
    private int detailIndex;
    private boolean allowed;

    public ReferenceDTO(Reference reference) {
        this.problem = reference.getProblem();
        this.resource = reference.getResource().getValue();
        this.type = reference.getType().getValue();
        this.positive = reference.getDetail().isPositive();
        this.detailIndex = reference.getDetail().getDetailIndex();
        this.allowed = reference.isAllowed();
    }
}
