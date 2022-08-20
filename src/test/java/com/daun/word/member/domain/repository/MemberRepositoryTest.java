package com.daun.word.member.domain.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName(value = "이메일과 소셜타입으로 회원을 조회한다")
    @Test
    void findByEmailAndSocialType() throws Exception {
        //given

        //when

        //then

    }

}
