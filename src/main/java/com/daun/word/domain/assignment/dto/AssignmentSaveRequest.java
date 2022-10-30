package com.daun.word.domain.assignment.dto;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.study.domain.Study;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AssignmentSaveRequest {
    @NotNull
    private final Email assignTo;
    @NotNull
    private final Integer problemId;
}
