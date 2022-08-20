package com.daun.word.workbook.domain.vo;

import com.daun.word.member.domain.vo.Nickname;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {
    private static Stream<Arguments> valid() {
        return Stream.of (
                Arguments.of("tester"),
                Arguments.of("저자명"),
                Arguments.of("하위루123")
        );
    }

    private static Stream<Arguments> invalid() {
        return Stream.of (
                Arguments.of(" "),
                Arguments.of("")
        );
    }

    @DisplayName(value = "저자명 생성할 수 있다")
    @MethodSource("valid")
    @ParameterizedTest
    void create(String str) throws Exception {
        //given&&when
        Author author = new Author(str);
        //then
        assertThat(author).isNotNull();
        assertAll(
                () -> assertThat(author.getValue()).isEqualTo(str)
        );
    }

    @DisplayName(value = "정책에 위반되는 저자명은 사용할 수 없다")
    @MethodSource("invalid")
    @ParameterizedTest
    void create_fail(String str) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new Author(str);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
