package com.daun.word.assignment.dto;

import lombok.Getter;

@Getter
public class SubmissionRequest {
    private Integer id;
    private String submission;

    public SubmissionRequest(Integer id, String submission) {
        this.id = id;
        this.submission = submission;
    }
}
