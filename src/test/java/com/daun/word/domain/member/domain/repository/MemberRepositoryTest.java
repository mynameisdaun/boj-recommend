package com.daun.word.domain.member.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Nickname;
import com.daun.word.domain.member.domain.vo.SocialType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.daun.word.Fixture.Fixture.member;
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
        Member member = new Member(new Email("new-member@weword.com"), "fake-password", new Nickname("new-member"), SocialType.W);
        //when
        int success = memberRepository.save(member);
        //then
        assertThat(success).isEqualTo(1);
    }

    @DisplayName(value = "회원 정보를 업데이트 할 수 있다")
    @Test
    void update() throws Exception {
        //given
        Member member = member();
        LocalDateTime before = member.getUpdatedAt();
        //when
        memberRepository.save(member);
        //then
        assertThat(member.getUpdatedAt()).isAfter(before);
    }

    @DisplayName(value = "이미 존재하는 이메일로는 회원을 등록할 수 없다")
    @Test
    void register_exist_email() throws Exception {
        //given
        Member member = new Member(member().getEmail(), "fake-password", new Nickname("new-member"), SocialType.W);
        //when&&then
        assertThatThrownBy(() -> {
            memberRepository.save(member);
        }).isInstanceOf(DuplicateKeyException.class);
    }


    @DisplayName(value = "존재하지 않는 이메일로 회원을 조회할 수 없다")
    @Test
    void findByEmailAndSocialType_no_exist_email() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            memberRepository.findByEmail(new Email("no-exist@weword.com"))
                    .orElseThrow(NoSuchElementException::new);
        }).isInstanceOf(NoSuchElementException.class);
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
