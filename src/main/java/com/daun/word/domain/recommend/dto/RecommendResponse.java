package com.daun.word.domain.recommend.dto;

import com.daun.word.domain.member.dto.MemberDTO;
import com.daun.word.domain.problem.dto.ProblemDTO;
import com.daun.word.domain.recommend.domain.Recommend;
import com.google.common.base.Preconditions;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
public class RecommendResponse {

    private String id;
    private ProblemDTO problem;
    private List<MemberDTO> members;

    public RecommendResponse(List<Recommend> recommends) {
        Preconditions.checkArgument(recommends != null);
        Preconditions.checkArgument(!recommends.isEmpty());
        this.id = recommends.get(0).getId().toString();
        this.problem = new ProblemDTO(recommends.get(0).getProblem());
        this.members = recommends.stream()
                .map(Recommend::getMember)
                .map(MemberDTO::new)
                .collect(Collectors.toList());
    }

}
