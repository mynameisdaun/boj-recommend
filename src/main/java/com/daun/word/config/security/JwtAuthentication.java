package com.daun.word.config.security;

import com.daun.word.global.Id;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import com.daun.word.domain.member.domain.vo.SocialType;
import lombok.Getter;
import lombok.ToString;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@ToString
public class JwtAuthentication {

    public final Id<Member, Integer> id;

    public final Email email;

    public final Name name;

    private final SocialType socialType;

    public JwtAuthentication(Id<Member, Integer> id, Email email, Name name, SocialType socialType) {
        checkArgument(id != null, "id must be provided.");
        checkArgument(name != null, "name must be provided.");
        checkArgument(email != null, "email must be provided.");
        this.id=id;
        this.name = name;
        this.email = email;
        this.socialType = socialType;
    }

}
