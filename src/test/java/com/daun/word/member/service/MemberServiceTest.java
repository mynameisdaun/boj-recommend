package com.daun.word.member.service;

import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.repository.MemberRepository;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.member.dto.RegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.daun.word.Fixture.Fixture.email;
import static com.daun.word.Fixture.Fixture.nickname;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;


    @DisplayName(value = "회원을 등록할 수 있다")
    @Test
    void register_success() throws Exception {
        //given
        RegisterRequest request = new RegisterRequest(nickname(), email(), SocialType.K);
        //when
        Member registered = memberService.register(request);
        //then
        verify(memberRepository, times(1)).register(any(Member.class));
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
        //given, when
        memberService.findByEmail(email());
        //then
        verify(memberRepository, times(1)).findByEmail(any(Email.class));
    }
}
