package com.daun.word.domain.member.dto;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.global.dto.TierDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterResponse {

    private String id;
    private String email;
    private String name;
    private TierDTO tier;

    public RegisterResponse(Member member) {
        this.id = member.getId().toString();
        this.email = member.getEmail().getValue();
        this.name = member.getName().getValue();
        this.tier = new TierDTO(member.getTier());
    }

}
