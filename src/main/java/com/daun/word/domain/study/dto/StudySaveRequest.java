package com.daun.word.domain.study.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class StudySaveRequest {
    private  Email leader;
    private  Name studyName;
    private  String key;
    private  List<Email> members;

    public StudySaveRequest(String leader, String studyName, String key, List<String> members) {
        this.leader = new Email(leader);
        this.studyName = new Name(studyName);
        this.key = key;
        this.members = members.stream().map(Email::new).collect(Collectors.toList());
    }

}
