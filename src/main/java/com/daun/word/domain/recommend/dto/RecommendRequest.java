package com.daun.word.domain.recommend.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.recommend.domain.vo.RecommendType;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class RecommendRequest {

    private List<String> emails;
    private RecommendType type;
    private RecommendSearchQuery query;

    public RecommendRequest(List<String> emails, RecommendType type, RecommendSearchQuery query) {
        this.emails = emails;
        this.type = type;
        this.query = query;
    }

    public RecommendRequest(Study study, RecommendType type, RecommendSearchQuery query) {
        this(study.getStudyMembers().stream().map(StudyMember::email).collect(Collectors.toList()), type, query);
    }

    public List<Email> emails() {
        return this.emails
                .stream()
                .map(Email::new)
                .collect(Collectors.toList());
    }

}
