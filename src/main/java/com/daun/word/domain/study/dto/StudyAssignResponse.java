package com.daun.word.domain.study.dto;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.global.vo.URL;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public final class StudyAssignResponse {
    private final List<String> studyProblems;
    private final List<MemberProblem> memberProblems;

    public StudyAssignResponse(List<Recommend> studyProblem, List<Recommend> recommends) {
        this.studyProblems = studyProblem.stream().map(Recommend::getProblem).map(Problem::getUrl).map(URL::getValue).collect(Collectors.toList());
        this.memberProblems = recommends.stream().map(MemberProblem::new).collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    static class MemberProblem {
        private String problemUrl;
        private String member;

        MemberProblem(Recommend r) {
            this.problemUrl = r.getProblem().getUrl().getValue();
            this.member = r.getMember().getEmail().getValue();
        }
    }
}
