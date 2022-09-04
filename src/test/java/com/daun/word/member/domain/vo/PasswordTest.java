package com.daun.word.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PasswordTest {

    private static Stream<Arguments> inValid() {

        return Stream.of(
                Arguments.of("1aA@"), // 적어도 8문자 이상
                Arguments.of("imTester!"), // 한개 이상의 숫자 포함
                Arguments.of("imtester9!"), // 적어도 하나 이상의 대문자 영어
                Arguments.of("IMTESTER9!"), // 적어도 하나 이상의 소문자 영어
                Arguments.of("imtester9"), // 적어도 하나 이상의 소문자 영어
                Arguments.of("iMtester! ") // 스페이스바나 탭 없음
        );
    }

    private static Stream<Arguments> valid() {
        return Stream.of(
                Arguments.of("iMtester9!")
        );
    }

    @DisplayName(value = "패스워드를 생성할 수 있다")
    @MethodSource("valid")
    @ParameterizedTest
    void create(String str) throws Exception {
        //given&&when
        Password password = new Password(str);
        //then
        assertThat(password).isNotNull();
        assertAll(
                () -> assertThat(password.getPassword()).isEqualTo(str)
        );
    }

    @DisplayName(value = "정책에 위반되는 비밀번호는 사용할 수 없다")
    @MethodSource("inValid")
    @ParameterizedTest
    void create_fail(String str) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new Password(str);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
