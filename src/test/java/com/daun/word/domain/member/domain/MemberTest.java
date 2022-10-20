package com.daun.word.domain.member.domain;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.global.vo.Tier;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.daun.word.Fixture.Fixture.email;
import static com.daun.word.Fixture.Fixture.nickname;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberTest {

    private static Stream<Arguments> inValid() {
        return Stream.of(
                Arguments.of(null, "fake-password", email(), new Tier(11),SocialType.K),
                Arguments.of(nickname(), null, email(), new Tier(11), SocialType.K),
                Arguments.of(nickname(), "fake-password", null, new Tier(11), SocialType.K),
                Arguments.of(nickname(), "fake-password", email(), new Tier(11), null),
                Arguments.of(nickname(), "fake-password", email(), new Tier(11), SocialType.K)
        );
    }

    @DisplayName(value = "회원을 생성한다")
    @Test
    @Disabled
    void create() throws Exception {
        //given,when
        Member member = new Member(email(), "fake-password", nickname(), new Tier(11),SocialType.K);
        //then
        assertThat(member).isNotNull();
        assertAll(
                () -> assertThat(member.getEmail()).isEqualTo(email()),
                () -> assertThat(member.getPassword()).isEqualTo("fake-password"),
                () -> assertThat(member.getName()).isEqualTo(nickname()),
                () -> assertThat(member.getSocialType()).isEqualTo(SocialType.K)
        );

    }

    @DisplayName(value = "회원 생성을 위해서는 이메일, 비밀번호, 닉네임, 소셜타입이 필수적으로 제공되어야 한다")
    @MethodSource("inValid")
    @ParameterizedTest
    @Disabled
    void create_fail(Name name, String password, Email email, Tier tier, SocialType socialType) throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            new Member(email, password, name, tier, socialType);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}