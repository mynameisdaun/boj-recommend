package com.daun.word.domain.assignment.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.Id;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class AssignmentSaveRequest {
    @NotNull
    private Id<Problem, Integer> problemId;
    @NotNull
    private Email assignFrom;
    @NotNull
    private Email assignTo;
    @NotNull
    private LocalDateTime startDateTime;
    @NotNull
    private LocalDateTime endDateTime;

    public AssignmentSaveRequest(Integer problemId, Email assignFrom, Email assignTo, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.problemId = Id.of(Problem.class, problemId);
        this.assignFrom = assignFrom;
        this.assignTo = assignTo;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
