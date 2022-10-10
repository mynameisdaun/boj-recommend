package com.daun.word.domain.study.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class StudySaveRequest {
    private final Email leader;
    private final Name studyName;
    private final String key;
    private final List<Email> members;

    public StudySaveRequest(String leader, String studyName, String key, List<String> members) {
        this.leader = new Email(leader);
        this.studyName = new Name(studyName);
        this.key = key;
        this.members = members.stream().map(Email::new).collect(Collectors.toList());
    }

}
