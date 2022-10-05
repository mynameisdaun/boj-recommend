package com.daun.word.domain.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class NicknameTest {
    private static Stream<Arguments> validNickname() {
        return Stream.of(
                Arguments.of("tester"),
                Arguments.of("테스터"),
                Arguments.of("하위루123")
        );
    }

    private static Stream<Arguments> inValidNickname() {
        return Stream.of(
                Arguments.of(" "),
                Arguments.of("")
        );
    }

    @DisplayName(value = "닉네임을 생성할 수 있다")
    @MethodSource("validNickname")
    @ParameterizedTest
    void create(String str) throws Exception {
        //given&&when
        Nickname nickname = new Nickname(str);
        //then
        assertThat(nickname).isNotNull();
        assertAll(
                () -> assertThat(nickname.getValue()).isEqualTo(str)
        );
    }

    @DisplayName(value = "정책에 위반되는 닉네임은 사용할 수 없다")
    @MethodSource("inValidNickname")
    @ParameterizedTest
    void create_fail(String str) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new Nickname(str);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
