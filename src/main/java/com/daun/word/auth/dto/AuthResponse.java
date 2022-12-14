package com.daun.word.auth.dto;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.dto.MemberDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    private final MemberDTO member;
    private final String accessToken;

    public AuthResponse(final Member member, final String accessToken) {
        this.member = new MemberDTO(member);
        this.accessToken = accessToken;
    }
}
