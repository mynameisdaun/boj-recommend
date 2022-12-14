package com.daun.word.domain.member.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.repository.FakeMemberRepository;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.member.dto.RegisterRequest;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.Tier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

import static com.daun.word.Fixture.Fixture.daun9870jung;
import static com.daun.word.Fixture.Fixture.member_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberServiceTest {

    private MemberService memberService;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void SetUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        FakeMemberRepository memberRepository = new FakeMemberRepository();
        memberRepository.save(daun9870jung());
        memberService = new MemberService(memberRepository, passwordEncoder);
    }


    @DisplayName(value = "이메일로 회원을 조회할 수 있다")
    @Test
    void findByEmail() throws Exception {
        //given&&when
        Member member = memberService.findByEmail(daun9870jung().getEmail());
        //then
        assertThat(member).isNotNull();
        assertAll(
                () -> assertThat(member.getId()).isEqualTo(daun9870jung().getId()),
                () -> assertThat(member.getEmail()).isEqualTo(daun9870jung().getEmail()),
                () -> assertThat(member.getName()).isEqualTo(daun9870jung().getName()),
                () -> assertThat(member.getSocialType()).isEqualTo(daun9870jung().getSocialType()),
                () -> assertThat(member.getLastLoginAt()).isEqualTo(daun9870jung().getLastLoginAt()),
                () -> assertThat(member.getLoginCount()).isEqualTo(daun9870jung().getLoginCount())
        );
    }

    @DisplayName(value = "존재하지 않는 회원 입니다")
    @Test
    void findByEmail_no_exist() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            memberService.findByEmail(member_2().getEmail());
        }).isInstanceOf(NoSuchElementException.class)
                .hasMessage("존재하지 않는 회원 입니다");
    }
}
