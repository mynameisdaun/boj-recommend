package com.daun.word.config.security;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@ToString
public class JwtAuthentication {

    public final UUID memberId;

    public final Email email;

    public JwtAuthentication(UUID memberId, Email email) {
        this.memberId = memberId;
        this.email = email;
    }

}
