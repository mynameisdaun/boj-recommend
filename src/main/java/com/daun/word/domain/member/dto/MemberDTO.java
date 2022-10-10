package com.daun.word.domain.member.dto;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import com.daun.word.domain.member.domain.vo.SocialType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDTO {
    private Integer id;
    private Email email;
    private Name name;
    private SocialType socialType;
    private int loginCount;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemberDTO (Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.socialType = member.getSocialType();
        this.loginCount = member.getLoginCount();
        this.lastLoginAt = member.getLastLoginAt();
        this.createdAt = member.getCreatedAt();
        this.updatedAt = member.getUpdatedAt();
    }
}
