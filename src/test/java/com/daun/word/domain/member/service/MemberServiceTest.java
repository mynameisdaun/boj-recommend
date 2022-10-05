package com.daun.word.domain.member.service;

import com.daun.word.global.auth.dto.AuthenticationRequest;
import com.daun.word.global.auth.dto.AuthenticationResponse;
import com.daun.word.global.auth.token.dto.TokenDTO;
import com.daun.word.global.auth.token.service.TokenService;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.repository.FakeMemberRepository;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Nickname;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.member.dto.MemberDTO;
import com.daun.word.domain.member.dto.RegisterRequest;
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
import static org.mockito.BDDMockito.given;


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
        memberService = new MemberService(tokenService, new FakeMemberRepository(), encoder);
    }

    @DisplayName(value = "회원을 등록할 수 있다")
    @Test
    void register() throws Exception {
        //given
        RegisterRequest request = new RegisterRequest("iwantNewMember@weword.com", "iWantBeNew@9", "songTTubi", "W");
        //when
        MemberDTO response = memberService.register(request);
        //then
        assertThat(response).isNotNull();
        assertAll(
                () -> assertThat(response.getEmail()).isEqualTo(new Email("iwantNewMember@weword.com")),
                () -> assertThat(response.getNickname()).isEqualTo(new Nickname("songTTubi")),
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
        RegisterRequest request = new RegisterRequest(member().getEmail().getValue(), "iWantBeNew@9", "songTTubi", "W");
        //when&&then
        assertThatThrownBy(() -> {
            memberService.register(request);
        }).isInstanceOf(DuplicateKeyException.class);
    }

    @DisplayName(value = "로그인을 할 수 있다")
    @Test
    void login() throws Exception {
        //given
        AuthenticationRequest request = new AuthenticationRequest(member().getEmail().getValue(), password().getValue(), member().getSocialType().name());
        given(tokenService.saveTokenFor(any(Member.class))).willReturn(new TokenDTO(token()));
        //when
        AuthenticationResponse response = memberService.login(request);
        //then
        assertThat(response).isNotNull();
        assertAll(
                () -> assertThat(response.getAccessToken()).isEqualTo(token().getAccessToken()),
                () -> assertThat(response.getRefreshToken()).isEqualTo(token().getRefreshToken()),
                () -> assertThat(response.getEmail()).isEqualTo(member().getEmail()),
                () -> assertThat(response.getNickname()).isEqualTo(member().getNickname()),
                () -> assertThat(response.getSocialType()).isEqualTo(member().getSocialType())
        );
    }

    @DisplayName(value = "존재하지 않는 이메일로 로그인 요청할 수 없다")
    @Test
    void login_fail_no_exist_email() throws Exception {
        //given
        AuthenticationRequest request = new AuthenticationRequest("no-exist@weword.com", password().getValue(), member().getSocialType().name());
        //when&&then
        assertThatThrownBy(() -> memberService.login(request) )
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("존재하지 않는 이메일 입니다.");
    }

    @DisplayName(value = "잘못된 비밀번호로 로그인 할 수 없다")
    @Test
    void login_wrong_pwd() throws Exception {
        //given
        AuthenticationRequest request = new AuthenticationRequest(member().getEmail().getValue(), "wrongPwd1!", member().getSocialType().name());
        //when&&then
        assertThatThrownBy(() -> memberService.login(request) )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호가 틀립니다.");
    }

    @DisplayName(value = "이메일로 회원을 조회할 수 있다")
    @Test
    void findByEmail() throws Exception {
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

    @DisplayName(value = "존재하지 않는 이메일로 로그인 요청할 수 없다")
    @Test
    void findByEmail_no_exist() throws Exception {

        //given&&when&&then
        assertThatThrownBy(() -> memberService.findByEmail(new Email("noexist@wewor.com")))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("존재하지 않는 이메일 입니다.");
    }
}
