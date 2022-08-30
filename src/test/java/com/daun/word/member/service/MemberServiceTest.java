package com.daun.word.member.service;

import com.daun.word.Fixture.Fixture;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.repository.FakeMemberRepository;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.member.dto.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.daun.word.Fixture.Fixture.*;
import static com.daun.word.Fixture.Fixture.email;
import static com.daun.word.Fixture.Fixture.nickname;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberServiceTest {

    private MemberService memberService;

    @BeforeEach
    public void SetUp() {
        memberService = new MemberService(new FakeMemberRepository());
    }

    @DisplayName(value = "회원을 등록할 수 있다")
    @Test
    void save() throws Exception {
        //given
        RegisterRequest request = new RegisterRequest(nickname(), email(), SocialType.K);
        //when
        Member registered = memberService.register(request);
        //then
        assertThat(registered).isNotNull();
        assertAll(
                () -> assertThat(registered).isEqualTo(new Member(email(), "fake-password", nickname(), SocialType.K)),
                () -> assertThat(registered.getEmail()).isEqualTo(email()),
                () -> assertThat(registered.getNickname()).isEqualTo(nickname()),
                () -> assertThat(registered.getSocialType()).isEqualTo(SocialType.K)
        );
    }

    @DisplayName(value = "올바른 요청이 아니라면 회원을 등록할 수 없다")
    @Test
    void register_fail() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            memberService.register(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName(value = "이메일과 소셜타입으로 회원을 조회할 수 있다")
    @Test
    void findMemberByEmailAndSocialType_success() throws Exception {
        //given&&when
        Member member = memberService.findByEmail(member().getEmail());
        //then
        assertThat(member).isNotNull();
        assertAll(
                () -> assertThat(member.getId()).isEqualTo(member().getId()),
                () -> assertThat(member.getEmail()).isEqualTo(member().getEmail()),
                () -> assertThat(member.getNickname()).isEqualTo(member().getNickname()),
                () -> assertThat(member.getSocialType()).isEqualTo(member().getSocialType()),
                () -> assertThat(member.getLastLoginAt()).isEqualTo(member().getLastLoginAt()),
                () -> assertThat(member.getLoginCount()).isEqualTo(member().getLoginCount())
        );
    }
}
