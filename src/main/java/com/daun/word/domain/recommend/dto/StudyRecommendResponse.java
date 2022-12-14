package com.daun.word.domain.recommend.dto;

import com.daun.word.domain.problem.dto.ProblemDTO;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.study.domain.Study;
import com.google.common.base.Preconditions;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StudyRecommendResponse {
    private String study;
    private ProblemDTO problem;

    public StudyRecommendResponse(Study study, List<Recommend> recommends) {
        Preconditions.checkArgument(recommends != null);
        Preconditions.checkArgument(!recommends.isEmpty());
        this.study = study.getId().toString();
        this.problem = new ProblemDTO(recommends.get(0).getProblem());
    }
}
