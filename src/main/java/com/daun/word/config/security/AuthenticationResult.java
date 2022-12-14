package com.daun.word.config.security;

import com.daun.word.domain.member.domain.Member;
import lombok.Data;

import static com.google.common.base.Preconditions.checkArgument;

@Data
public class AuthenticationResult {

    private final String apiToken;

    private final Member member;

    public AuthenticationResult(String apiToken, Member member) {
        checkArgument(apiToken != null, "apiToken must be provided.");
        checkArgument(member != null, "member must be provided.");

        this.apiToken = apiToken;
        this.member = member;
    }

}
