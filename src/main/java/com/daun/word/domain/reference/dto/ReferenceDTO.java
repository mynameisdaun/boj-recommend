package com.daun.word.domain.reference.dto;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.reference.domain.Reference;
import com.daun.word.global.vo.URL;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReferenceDTO {

    private Problem problem;
    private String url;
    private boolean allowed;

    public ReferenceDTO(Reference reference) {
        this.problem = reference.getProblem();
        this.url = reference.getUrl().getValue();
        this.allowed = reference.isAllowed();
    }
}
