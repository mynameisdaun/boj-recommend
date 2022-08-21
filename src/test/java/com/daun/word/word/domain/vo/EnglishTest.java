package com.daun.word.word.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class EnglishTest {

    private static Stream<Arguments> invalid() {
        return Stream.of(
                Arguments.of(" "),
                Arguments.of(""),
                Arguments.of("test1"),
                Arguments.of("한국말"),
                Arguments.of("あいうえお")
        );
    }

    @DisplayName(value = "영문표기를 생성할 수 있다")
    @Test
    void create() throws Exception {
        //given&&when
        English english = new English("word");
        //then
        assertThat(english).isNotNull();
        assertAll(
                () -> assertThat(english.getValue()).isEqualTo("word")
        );
    }

    @DisplayName(value = "영문 표기는 반드시 한글자 이상의 영문만 포함하고 있어야 한다.")
    @MethodSource("invalid")
    @ParameterizedTest
    void create_fail(String str) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new English(str);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
