package com.daun.word.domain.study.dto;

import com.daun.word.domain.study.domain.Study;

import java.util.List;
import java.util.stream.Collectors;

public class StudyDTO {

    private String id;
    private String leader;
    private List<String> members;

    public StudyDTO(Study study) {
        this.id = study.getId().toString();
        this.leader = study.getLeader().getEmail().getValue();
        this.members = study.getStudyMembers()
                .stream()
                .map(sm -> sm.getMember().getEmail().getValue())
                .collect(Collectors.toList());
    }


}
