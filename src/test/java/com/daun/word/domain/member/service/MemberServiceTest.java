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

import java.util.NoSuchElementException;

import static com.daun.word.Fixture.Fixture.daun9870jung;
import static com.daun.word.Fixture.Fixture.member_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberServiceTest {

    private MemberService memberService;

    @BeforeEach
    public void SetUp() {
        FakeMemberRepository memberRepository = new FakeMemberRepository();
        memberRepository.save(daun9870jung());
        memberService = new MemberService(memberRepository, new BCryptPasswordEncoder());
    }

    @DisplayName(value = "회원을 등록한다")
    @Test
    void register() throws Exception {
        //given
        RegisterRequest request = new RegisterRequest("iwantNewMember@weword.com", "iWantBeNew@9", "songTTubi", "W", new Tier(1));
        //when
        Member member = memberService.register(request);
        //then
        assertThat(member).isNotNull();
        assertAll(
                () -> assertThat(member.getEmail()).isEqualTo(new Email("iwantNewMember@weword.com")),
                () -> assertThat(member.getName()).isEqualTo(new Name("songTTubi")),
                () -> assertThat(member.getSocialType()).isEqualTo(SocialType.W)
        );
    }

    @DisplayName(value = "잘못된 회원가입 요청입니다")
    @Test
    void register_fail() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            memberService.register(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 회원가입 요청입니다");
    }

    @DisplayName(value = "이미 가입한 회원입니다")
    @Test
    void register_fail_exist_email() throws Exception {
        //given
        RegisterRequest request = new RegisterRequest(daun9870jung().getEmail().getValue(), "iWantBeNew@9", "songTTubi", "W", new Tier(1));
        //when&&then
        assertThatThrownBy(() -> {
            memberService.register(request);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 가입한 회원입니다");
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
