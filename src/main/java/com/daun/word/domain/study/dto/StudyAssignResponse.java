package com.daun.word.domain.study.dto;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.recommend.domain.Recommend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class StudyAssignResponse {
    private List<Problem> studyProblem;
    private List<Recommend> recommends;
}
