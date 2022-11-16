package com.daun.word.domain.recommend.dto;

import com.daun.word.domain.member.dto.MemberDTO;
import com.daun.word.domain.problem.controller.ProblemDTO;
import com.daun.word.domain.recommend.domain.Recommend;

public class RecommendResponse {

    private String id;
    private ProblemDTO problem;
    private MemberDTO member;

    public RecommendResponse(Recommend recommend) {
        this.id = recommend.getId().toString();
        this.problem = new ProblemDTO(recommend.getProblem());
        this.member = new MemberDTO(recommend.getMember());
    }

}