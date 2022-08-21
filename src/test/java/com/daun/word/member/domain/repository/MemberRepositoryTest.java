package com.daun.word.member.domain.repository;

import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import com.daun.word.member.domain.vo.SocialType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.NoSuchElementException;

import static com.daun.word.Fixture.Fixture.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@MybatisTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName(value = "회원을 등록할 수 있다")
    @Test
    void register() throws Exception {
        //given
        Member member = new Member(new Email("new-member@weword.com"), "fake-password", new Nickname("new-member"), SocialType.W);
        //when
        int success = memberRepository.register(member);
        //then
        assertThat(success).isEqualTo(1);
    }

    @DisplayName(value = "이미 존재하는 이메일로는 회원을 등록할 수 없다")
    @Test
    void register_exist_email() throws Exception {
        //given
        Member member = new Member(member().getEmail(), "fake-password", new Nickname("new-member"), SocialType.W);
        //when&&then
        assertThatThrownBy(() -> {
            memberRepository.register(member);
        }).isInstanceOf(DuplicateKeyException.class);
    }


    @DisplayName(value = "이메일과 소셜타입으로 회원을 조회한다")
    @Test
    void findByEmailAndSocialType() throws Exception {
        //given&&when
        Member member = memberRepository.findMemberByEmailAndSocialType(member().getEmail(), member().getSocialType())
                .orElseThrow(NoSuchElementException::new);
        //then
        assertThat(member).isNotNull();
        assertAll(
                () -> assertThat(member).isEqualTo(member()),
                () -> assertThat(member.getEmail()).isEqualTo(member.getEmail()),
                () -> assertThat(member.getNickname()).isEqualTo(member.getNickname()),
                () -> assertThat(member.getSocialType()).isEqualTo(member.getSocialType())
        );
    }

    @DisplayName(value = "존재하지 않는 이메일로 회원을 조회할 수 없다")
    @Test
    void findByEmailAndSocialType_no_exist_email() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            memberRepository.findMemberByEmailAndSocialType(new Email("no-exist@weword.com"), SocialType.W)
                    .orElseThrow(NoSuchElementException::new);
        }).isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName(value = "존재하는 이메일이라도, 소셜 타입이 다르다면 회원을 조회할 수 없다")
    @Test
    void findByEmailAndSocialType_wrong_social_type() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            memberRepository.findMemberByEmailAndSocialType(member().getEmail(), SocialType.N)
                    .orElseThrow(NoSuchElementException::new);
        }).isInstanceOf(NoSuchElementException.class);
    }
}
