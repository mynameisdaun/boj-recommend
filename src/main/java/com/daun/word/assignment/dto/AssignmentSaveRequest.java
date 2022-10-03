package com.daun.word.assignment.dto;

import com.daun.word.assignment.domain.PAssignment;
import com.daun.word.global.Id;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.problem.domain.Problem;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class AssignmentSaveRequest {
    @NotNull
    private Id<PAssignment, Integer> id;
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

    public AssignmentSaveRequest(Integer id, Integer problemId, Email assignFrom, Email assignTo, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.id = Id.of(PAssignment.class, id);
        this.problemId = Id.of(Problem.class, id);
        this.assignFrom = assignFrom;
        this.assignTo = assignTo;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
