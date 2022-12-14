package com.daun.word.domain.member.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.repository.FakeMemberRepository;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.member.dto.RegisterRequest;
import com.daun.word.global.vo.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.daun.word.Fixture.Fixture.daun9870jung;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;

public class RegisterServiceTest {

    private RegisterService registerService;

    @BeforeEach
    public void SetUp() {
        FakeMemberRepository memberRepository = new FakeMemberRepository();
        memberRepository.save(daun9870jung());
        registerService = new RegisterService(memberRepository, null, null, null, null);
    }

    @DisplayName(value = "회원을 등록한다")
    @Test
    void register() throws Exception {
        //given
        RegisterRequest request = new RegisterRequest("iwantNewMember@weword.com", "iWantBeNew@9", "songTTubi");
        //when
        Member member = null;
        //then
        fail();
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
            registerService.register(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 회원가입 요청입니다");
    }

    @DisplayName(value = "이미 가입한 회원입니다")
    @Test
    void register_fail_exist_email() throws Exception {
        //given
        RegisterRequest request = new RegisterRequest(daun9870jung().getEmail().getValue(), "iWantBeNew@9", "songTTubi");
        //when&&then
        assertThatThrownBy(() -> {
            registerService.register(request);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 가입한 회원입니다");
    }
}
