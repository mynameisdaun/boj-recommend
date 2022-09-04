package com.daun.word.member.dto;

import com.daun.word.member.domain.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDTO {
    private Integer id;
    private String email;
    private String nickname;
    private String socialType;
    private int loginCount;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemberDTO (Member member) {
        this.id = member.getId();
        this.email = member.getEmail().getValue();
        this.nickname = member.getNickname().getValue();
        this.socialType = member.getSocialType().name();
        this.loginCount = member.getLoginCount();
        this.lastLoginAt = member.getLastLoginAt();
        this.createdAt = member.getCreatedAt();
        this.updatedAt = member.getUpdatedAt();
    }
}
