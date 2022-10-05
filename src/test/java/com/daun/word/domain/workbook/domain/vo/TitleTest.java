package com.daun.word.domain.workbook.domain.vo;

import com.daun.word.global.vo.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class TitleTest {
    private static Stream<Arguments> valid() {
        return Stream.of(
                Arguments.of("tester"),
                Arguments.of("타이틀"),
                Arguments.of("하위루123")
        );
    }

    private static Stream<Arguments> invalid() {
        return Stream.of(
                Arguments.of(" "),
                Arguments.of("")
        );
    }

    @DisplayName(value = "타이틀을 생성할 수 있다")
    @MethodSource("valid")
    @ParameterizedTest
    void create(String str) throws Exception {
        //given&&when
        Title title = new Title(str);
        //then
        assertThat(title).isNotNull();
        assertAll(
                () -> assertThat(title.getValue()).isEqualTo(str)
        );
    }

    @DisplayName(value = "정책에 위반되는 저자명은 사용할 수 없다")
    @MethodSource("invalid")
    @ParameterizedTest
    void create_fail(String str) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new Title(str);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
