package com.daun.word.domain.study.dto;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.Tier;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static com.daun.word.Fixture.Fixture.email;
import static com.daun.word.Fixture.Fixture.nickname;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class StudySaveRequestTest {

    private static Stream<Arguments> inValid() {
        return Stream.of(
                Arguments.of(new ArrayList<>())
        );
    }

    @DisplayName(value = "StudySaveRequest를 생성한다")
    @Test
    void create() throws Exception {
        //given&&when
        StudySaveRequest request = new StudySaveRequest("tester1", "name", "secret-key", Arrays.asList("tester1", "tester2"));
        //then
        assertThat(request).isNotNull();
        assertAll(
                () -> assertThat(request.getLeader().getValue()).isEqualTo("tester1"),
                () -> assertThat(request.getStudyName().getValue()).isEqualTo("name"),
                () -> assertThat(request.getKey()).isEqualTo("secret-key"),
                () -> assertThat(request.getMembers().size()).isEqualTo(2),
                () -> assertThat(request.getMembers()).containsAll(Arrays.asList(new Email("tester1"), new Email("tester2")))
        );
    }

    @DisplayName(value = "스터디 리더 지정이 필요합니다")
    @NullAndEmptySource
    @ParameterizedTest
    void create_fail_leader(final String leader) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new StudySaveRequest(leader, "name", "secret-key", Arrays.asList("tester1", "tester2"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("스터디 리더 지정이 필요합니다");
    }

    @DisplayName(value = "스터디 이름은 필수입력 사항 입니다")
    @NullAndEmptySource
    @ParameterizedTest
    void create_fail_study_name(final String name) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new StudySaveRequest("tester1", name, "secret-key", Arrays.asList("tester1", "tester2"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("스터디 이름은 필수입력 사항 입니다");
    }

    @DisplayName(value = "스터디 비밀번호 key는 4글자 이상이어야 합니다")
    @ValueSource(strings = "12")
    @NullAndEmptySource
    @ParameterizedTest
    void create_fail_key(final String key) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new StudySaveRequest("tester1", "name", key, Arrays.asList("tester1", "tester2"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("스터디 비밀번호 key는 4글자 이상이어야 합니다");
    }

    @DisplayName(value = "1명 이상의 스터디원이 있어야 합니다")
    @MethodSource("inValid")
    @NullSource
    @ParameterizedTest
    void create_fail_members(final List<String> members) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new StudySaveRequest("tester1", "name", "secret-key", members);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1명 이상의 스터디원이 있어야 합니다");
    }
}
