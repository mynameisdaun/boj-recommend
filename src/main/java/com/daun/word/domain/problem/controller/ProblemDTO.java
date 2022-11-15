package com.daun.word.domain.problem.controller;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.dto.TierDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class ProblemDTO {

    private final Integer id;

    private final String title;

    private final String url;

    private final TierDTO tier;

    private final List<TagDTO> tags;

    public ProblemDTO(Problem problem) {
        this.id = problem.getId();
        this.title = problem.getTitle().getValue();
        this.url = problem.getUrl().getValue();
        this.tier = new TierDTO(problem.getTier());
        this.tags = problem.getProblemTags()
                .stream()
                .map(TagDTO::new)
                .collect(Collectors.toList());
    }

}
