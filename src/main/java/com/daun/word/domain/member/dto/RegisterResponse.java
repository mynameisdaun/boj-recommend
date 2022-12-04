package com.daun.word.domain.member.dto;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.dto.ProblemDTO;
import com.daun.word.global.dto.TierDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RegisterResponse {

    private String id;
    private String email;
    private String name;
    private TierDTO tier;
    private List<ProblemDTO> problems;

    public RegisterResponse(List<Assignment> assignments) {
        if (assignments == null || assignments.isEmpty()) {
            throw new IllegalStateException("");
        }
        Member member = assignments.get(0).getMember();
        this.id = member.getId().toString();
        this.email = member.getEmail().getValue();
        this.name = member.getName().getValue();
        this.tier = new TierDTO(member.getTier());
        this.problems = assignments.stream()
                .map(a -> new ProblemDTO(a.getProblem()))
                .collect(Collectors.toList());
    }

}
