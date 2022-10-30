package com.daun.word.domain.study.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class StudySaveRequest {
    private Email leader;
    private Name studyName;
    private String key;
    private List<Email> members;

    public StudySaveRequest(String leader, String studyName, String key, List<String> members) {
        Preconditions.checkArgument(leader != null && !leader.trim().equals(""), "스터디 리더 지정이 필요합니다");
        Preconditions.checkArgument(studyName != null && !studyName.trim().equals(""), "스터디 이름은 필수입력 사항 입니다");
        Preconditions.checkArgument(key != null && key.trim().length() >= 4, "스터디 비밀번호 key는 4글자 이상이어야 합니다");
        Preconditions.checkArgument(members != null && !members.isEmpty(), "1명 이상의 스터디원이 있어야 합니다");
        this.leader = new Email(leader);
        this.studyName = new Name(studyName);
        this.key = key;
        this.members = members.stream().map(Email::new).collect(Collectors.toList());
    }

}
