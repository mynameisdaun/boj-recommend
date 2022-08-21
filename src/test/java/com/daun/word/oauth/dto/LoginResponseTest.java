package com.daun.word.oauth.dto;

import com.daun.word.member.domain.Member;
import com.daun.word.oauth.token.domain.Token;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.daun.word.Fixture.Fixture.member;
import static com.daun.word.Fixture.Fixture.token;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoginResponseTest {

    private static Stream<Arguments> invalid() {
        return Stream.of(
                Arguments.of(member(), null),
                Arguments.of(null, token())
        );
    }

    @DisplayName(value = "로그인 응답을 생성한다")
    @Test
    void create() throws Exception {
        //given&&when
        LoginResponse loginResponse = new LoginResponse(member(), token());
        //then
        assertThat(loginResponse).isNotNull();
        assertAll(
                () -> assertThat(loginResponse.getMember()).isEqualTo(member()),
                () -> assertThat(loginResponse.getToken()).isEqualTo(token())
        );
    }

    @DisplayName(value = "회원 정보와 토큰 정보가 없으면 로그인 응답을 생성할 수 없다")
    @MethodSource("invalid")
    @ParameterizedTest
    void create_fail(Member member, Token token) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new LoginResponse(member, token);
        }).isInstanceOf(IllegalArgumentException.class);

    }

}
