package com.daun.word.Fixture;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;

public class Fixture {

    public static Email email() {
        return new Email("tester@tester.com");
    }

    public static Nickname nickname() {
        return new Nickname("tester");
    }
}
