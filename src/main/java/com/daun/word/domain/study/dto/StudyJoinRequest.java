package com.daun.word.domain.study.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class StudyJoinRequest {

    private UUID studyId;

}
