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
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
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
                Arguments.of(null, email(), new Name("name"), "fake-password", new Tier(11), SocialType.K),
                Arguments.of(UUID.randomUUID(), null, new Name("name"), "fake-password", new Tier(11), SocialType.K),
                Arguments.of(UUID.randomUUID(), email(), null, "fake-password", new Tier(11), SocialType.K),
                Arguments.of(UUID.randomUUID(), email(), new Name("name"), null, new Tier(11), SocialType.K),
                Arguments.of(UUID.randomUUID(), email(), new Name("name"), "fake-password", null, SocialType.K),
                Arguments.of(UUID.randomUUID(), email(), new Name("name"), "fake-password", new Tier(11), null)
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
    @ParameterizedTest
    void create_fail_no_leader() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new StudySaveRequest("tester1", "name", "secret-key", Arrays.asList("tester1", "tester2"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("스터디 리더 지정이 필요합니다");
    }
}
