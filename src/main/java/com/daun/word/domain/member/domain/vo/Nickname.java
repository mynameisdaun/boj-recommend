package com.daun.word.domain.member.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.daun.word.global.utils.StringUtils.isNullOrBlank;

@Getter
@EqualsAndHashCode
@ToString
public class Nickname {
    private final String nickname;

    public Nickname(String nickname) {
        if (isNullOrBlank(nickname)) {
            throw new IllegalArgumentException("정책에 맞지 않는 닉네임 입니다.");
        }
        this.nickname = nickname;
    }

    public String getValue() {
        return this.nickname;
    }
}
