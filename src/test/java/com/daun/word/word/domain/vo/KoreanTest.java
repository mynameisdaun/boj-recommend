package com.daun.word.word.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class KoreanTest {

    private static Stream<Arguments> valid() {
        return Stream.of(
                Arguments.of("할 수 있는"),
                Arguments.of("- 하다 할 수 있는"),
                Arguments.of("단어"),
                Arguments.of("english"),
                Arguments.of("…에 반대하여"),
                Arguments.of("~에 반대하여"),
                Arguments.of(" - 에 반대하여"),
                Arguments.of("다만 홀로(혼자서)"),
                Arguments.of("A.M. 오전"),
                Arguments.of("나, 그리고 너"),
                Arguments.of("10억"),
                Arguments.of("나(=너)")
        );
    }

    private static Stream<Arguments> invalid() {
        return Stream.of(
                Arguments.of(" "),
                Arguments.of(""),
                Arguments.of("あいうえお")
        );
    }

    @DisplayName(value = "한국어 표기를 생성할 수 있다")
    @MethodSource("valid")
    @ParameterizedTest
    void create(String str) throws Exception {
        //given&&when
        Korean korean = new Korean(str);
        //then
        assertThat(korean).isNotNull();
        assertAll(
                () -> assertThat(korean.getValue()).isEqualTo(str.trim())
        );
    }

    @DisplayName(value = "한국어 표기는 반드시 한글자 이상의 한국어만 포함하고 있어야 한다.")
    @MethodSource("invalid")
    @ParameterizedTest
    void create_fail(String str) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new Korean(str);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
