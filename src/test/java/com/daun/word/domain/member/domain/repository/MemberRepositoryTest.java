package com.daun.word.domain.member.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.global.vo.Tier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName(value = "회원을 등록할 수 있다")
    @Test
    void register() throws Exception {
        //given
        Member member = new Member(new Email("new-member@weword.com"), "fake-password", new Name("new-member"), new Tier(11), SocialType.W);
        //when
        int success = memberRepository.save(member);
        //then
        assertThat(success).isEqualTo(1);
    }


    @DisplayName(value = "이메일로 회원을 조회한다")
    @Test
    void findByEmail() throws Exception {
        //given
        Email email = new Email("daun9870jung");
        //when
        Member member = memberRepository.findByEmail(email).orElseThrow(NoSuchElementException::new);
        //then
        assertThat(member).isNotNull();
        assertAll(
                () -> assertThat(member.getEmail()).isEqualTo(email)
        );
    }
}
