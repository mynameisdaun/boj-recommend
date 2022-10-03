package com.daun.word.assignment.dto;

import com.daun.word.member.domain.vo.Email;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class d_AssignmentSaveRequest {
    @NotNull
    private Integer workbookId;
    @NotNull
    private Email assignFrom;
    @NotNull
    private Email assignTo;
    @NotNull
    private List<AssignmentDetailSaveRequest> details;

    public d_AssignmentSaveRequest(Integer workbookId, String assignFrom, String assignTo, List<AssignmentDetailSaveRequest> details) {
        this.workbookId = workbookId;
        this.assignFrom = new Email(assignFrom);
        this.assignTo = new Email(assignTo);
        this.details = details;
    }

    @Data
    public static class AssignmentDetailSaveRequest {
        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        @NotNull
        private Integer chapterId;
        @FutureOrPresent
        private LocalDateTime startDateTime;
        @Future
        private LocalDateTime endDateTime;
        @NotBlank
        private String quiz;

        public AssignmentDetailSaveRequest(Integer chapterId, String startDateTime, String endDateTime, String quiz) {
            this.chapterId = chapterId;
            this.startDateTime = LocalDateTime.parse(startDateTime, formatter);
            this.endDateTime = LocalDateTime.parse(endDateTime, formatter);
            this.quiz = quiz;
        }
    }
}
