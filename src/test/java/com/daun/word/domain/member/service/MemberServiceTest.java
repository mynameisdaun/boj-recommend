package com.daun.word.domain.member.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.repository.FakeMemberRepository;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.member.dto.RegisterRequest;
import com.daun.word.global.auth.token.service.TokenService;
import com.daun.word.global.vo.Tier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private TokenService tokenService;

    private PasswordEncoder encoder;

    @BeforeEach
    public void SetUp() {
        encoder = new BCryptPasswordEncoder();
        memberService = new MemberService(new FakeMemberRepository(), encoder);
    }

    @DisplayName(value = "회원을 등록할 수 있다")
    @Test
    void register() throws Exception {
        //given
        RegisterRequest request = new RegisterRequest("iwantNewMember@weword.com", "iWantBeNew@9", "songTTubi", "W", new Tier(1));
        //when
        Member response = memberService.register(request);
        //then
        assertThat(response).isNotNull();
        assertAll(
                () -> assertThat(response.getEmail()).isEqualTo(new Email("iwantNewMember@weword.com")),
                () -> assertThat(response.getName()).isEqualTo(new Name("songTTubi")),
                () -> assertThat(response.getSocialType()).isEqualTo(SocialType.W)
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

    @DisplayName(value = "중복된 이메일로 회원을 등록할 수 없다")
    @Test
    void register_fail_exist_email() throws Exception {
        //given
        RegisterRequest request = new RegisterRequest(member_3().getEmail().getValue(), "iWantBeNew@9", "songTTubi", "W", new Tier(1));
        //when&&then
        assertThatThrownBy(() -> {
            memberService.register(request);
        }).isInstanceOf(DuplicateKeyException.class);
    }

    @DisplayName(value = "로그인을 할 수 있다")
    @Test
    void login() throws Exception {

    }

    @DisplayName(value = "존재하지 않는 이메일로 로그인 요청할 수 없다")
    @Test
    void login_fail_no_exist_email() throws Exception {

    }

    @DisplayName(value = "잘못된 비밀번호로 로그인 할 수 없다")
    @Test
    void login_wrong_pwd() throws Exception {

    }

    @DisplayName(value = "이메일로 회원을 조회할 수 있다")
    @Test
    void findByEmail() throws Exception {
        //given&&when
        Member member = memberService.findByEmail(member_3().getEmail());
        //then
        assertThat(member).isNotNull();
        assertAll(
                () -> assertThat(member.getId()).isEqualTo(member_3().getId()),
                () -> assertThat(member.getEmail()).isEqualTo(member_3().getEmail()),
                () -> assertThat(member.getName()).isEqualTo(member_3().getName()),
                () -> assertThat(member.getSocialType()).isEqualTo(member_3().getSocialType()),
                () -> assertThat(member.getLastLoginAt()).isEqualTo(member_3().getLastLoginAt()),
                () -> assertThat(member.getLoginCount()).isEqualTo(member_3().getLoginCount())
        );
    }

    @DisplayName(value = "존재하지 않는 이메일로 로그인 요청할 수 없다")
    @Test
    void findByEmail_no_exist() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> memberService.findByEmail(new Email("noexist@wewor.com")))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("존재하지 않는 이메일 입니다.");
    }
}
