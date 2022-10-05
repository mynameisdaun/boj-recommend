package com.daun.word.domain.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class EmailTest {

    private static Stream<Arguments> inValidEmail() {
        return Stream.of(
                Arguments.of("plainaddress"),
                Arguments.of("#@%^%#$@#$@#.com"),
                Arguments.of("@example.com"),
                Arguments.of("Joe Smith <email@example.com>"),
                Arguments.of("email.example.com"),
                Arguments.of("email@example@example.com"),
                Arguments.of(".email@example.com"),
                Arguments.of("email.@example.com"),
                Arguments.of("email..email@example.com"),
                Arguments.of("あいうえお@example.com"),
                Arguments.of("email@example.com (Joe Smith)"),
                Arguments.of("email@example"),
                Arguments.of("email@-example.com"),
                Arguments.of("email@111.222.333.44444"),
                Arguments.of("email@example..com"),
                Arguments.of("Abc..123@example.com"),
                Arguments.of("firstname+lastname@example.com"),
                Arguments.of("email@123.123.123.123")
        );
    }

    private static Stream<Arguments> validEmail() {
        return Stream.of(
                Arguments.of("email@example.com"),
                Arguments.of("firstname.lastname@example.com"),
                Arguments.of("email@subdomain.example.com"),
                Arguments.of("1234567890@example.com"),
                Arguments.of("email@example-one.com"),
                Arguments.of("_______@example.com"),
                Arguments.of("email@example.museum"),
                Arguments.of("email@example.name"),
                Arguments.of("email@example.web"),
                Arguments.of("firstname-lastname@example.com")
        );
    }

    @DisplayName(value = "Email을 생성할 수 있다")
    @MethodSource("validEmail")
    @ParameterizedTest
    void create(String str) throws Exception {
        //given&&when
        Email email = new Email(str);
        //then
        assertThat(email).isNotNull();
        assertAll(
                () -> assertThat(email.getEmail()).isEqualTo(str)
        );
    }

    @DisplayName(value = "정책에 위반되는 Email은 사용할 수 없다")
    @MethodSource("inValidEmail")
    @ParameterizedTest
    void create_fail(String str) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new Email(str);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
