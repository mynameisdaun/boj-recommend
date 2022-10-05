package com.daun.word.domain.assignment.dto;

import lombok.Getter;

@Getter
public class SubmissionRequest {
    private final Integer id;
    private final String submission;

    public SubmissionRequest(Integer id, String submission) {
        this.id = id;
        this.submission = submission;
    }
}
