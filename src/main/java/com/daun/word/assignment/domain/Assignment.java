package com.daun.word.assignment.domain;

import com.daun.word.assignment.dto.d_AssignmentSaveRequest;
import com.daun.word.member.domain.vo.Email;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Assignment {
    private Integer id;
    private final Email assignFrom;
    private final Email assignTo;
    private final Integer workbookId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Assignment(Email assignFrom, Email assignTo, Integer workbookId) {
        this.assignFrom = assignFrom;
        this.assignTo = assignTo;
        this.workbookId = workbookId;
    }

    public Assignment(Integer id, Email assignFrom, Email assignTo, Integer workbookId) {
        this.id = id;
        this.assignFrom = assignFrom;
        this.assignTo = assignTo;
        this.workbookId = workbookId;
    }

    public Assignment(Integer id, String assignFrom, String assignTo, Integer workbookId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.assignFrom = new Email(assignFrom);
        this.assignTo = new Email(assignTo);
        this.workbookId = workbookId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Assignment fromSaveRequest(d_AssignmentSaveRequest request) {
        return new Assignment(request.getAssignFrom(), request.getAssignTo(), request.getWorkbookId());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
