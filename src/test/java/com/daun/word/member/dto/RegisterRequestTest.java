package com.daun.word.member.dto;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import com.daun.word.member.domain.vo.SocialType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.daun.word.Fixture.Fixture.email;
import static com.daun.word.Fixture.Fixture.nickname;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class RegisterRequestTest {

    private static Stream<Arguments> inValid() {
        return Stream.of(
                Arguments.of(null, email(), SocialType.K),
                Arguments.of(nickname(), null, SocialType.K),
                Arguments.of(nickname(), email(), null)
        );
    }

    @DisplayName(value = "회원가입 요청을 생성한다")
    @Test
    void create() throws Exception {
        //given,when
        RegisterRequest request = new RegisterRequest(nickname(), email(), SocialType.K);
        //then
        assertThat(request).isNotNull();
        assertAll(
                () -> assertThat(request.getEmail()).isEqualTo(email()),
                () -> assertThat(request.getNickname()).isEqualTo(nickname()),
                () -> assertThat(request.getSocialType()).isEqualTo(SocialType.K)
        );

    }

    @DisplayName(value = "회원가입을 위해서는 이메일, 닉네임, 소셜타입이 필수적으로 제공되어야 한다")
    @MethodSource("inValid")
    @ParameterizedTest
    void create_fail(Nickname nickname, Email email, SocialType socialType) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new RegisterRequest(nickname, email, socialType);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
