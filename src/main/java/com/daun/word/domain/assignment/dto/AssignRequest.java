package com.daun.word.domain.assignment.dto;

import com.daun.word.domain.member.domain.vo.Email;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AssignRequest {
    @NotNull
    private final Email assignTo;
    @NotNull
    private final Integer problemId;
}
