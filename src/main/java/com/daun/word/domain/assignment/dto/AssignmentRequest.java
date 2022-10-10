package com.daun.word.domain.assignment.dto;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.study.domain.Study;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AssignmentRequest {
    @NotNull
    private final Study study;
    @NotNull
    private final Recommend recommend;
    @NotNull
    private final Member assignTo;
    @NotNull
    private final LocalDateTime startDateTime;
    @NotNull
    private final LocalDateTime endDateTime;
}
