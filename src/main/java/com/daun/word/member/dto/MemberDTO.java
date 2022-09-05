package com.daun.word.member.dto;

import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import com.daun.word.member.domain.vo.SocialType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDTO {
    private Integer id;
    private Email email;
    private Nickname nickname;
    private SocialType socialType;
    private int loginCount;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemberDTO (Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.socialType = member.getSocialType();
        this.loginCount = member.getLoginCount();
        this.lastLoginAt = member.getLastLoginAt();
        this.createdAt = member.getCreatedAt();
        this.updatedAt = member.getUpdatedAt();
    }
}
