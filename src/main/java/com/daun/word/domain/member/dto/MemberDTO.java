package com.daun.word.domain.member.dto;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.global.vo.Name;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class MemberDTO {
    private UUID id;
    private Email email;
    private Name name;
    private SocialType socialType;
    private int loginCount;
    private Date lastLoginAt;
    private Date createdAt;
    private Date updatedAt;

    public MemberDTO (Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.socialType = member.getSocialType();
        this.loginCount = member.getLoginCount();
        this.lastLoginAt = member.getLastLoginAt();
        this.createdAt = member.getCreatedAt().getCreatedAt();
        this.updatedAt = member.getUpdatedAt().getUpdatedAt();
    }
}
