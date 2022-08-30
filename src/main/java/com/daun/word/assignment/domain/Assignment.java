package com.daun.word.assignment.domain;

import com.daun.word.assignment.dto.AssignmentSaveRequest;
import com.daun.word.member.domain.vo.Email;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Getter
@ToString
public class Assignment {
    private final Logger logger = LoggerFactory.getLogger(Assignment.class);
    private final Email assignFrom;
    private final Email assignTo;
    private final Integer workbookId;
    private Integer id;
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

    public static Assignment fromSaveRequest(AssignmentSaveRequest request) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assignment that = (Assignment) o;

        return logger != null ? logger.equals(that.logger) : that.logger == null;
    }

    @Override
    public int hashCode() {
        return logger != null ? logger.hashCode() : 0;
    }
}
