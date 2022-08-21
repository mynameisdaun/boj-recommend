package com.daun.word.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static com.daun.word.Fixture.Fixture.email;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class JwtUtilsTest {

    private JwtUtils jwtUtils;

    @BeforeEach
    public void setUp() {
        this.jwtUtils = new JwtUtils("testaccesskeytestaccesskeytestaccesskeytestaccesskeytestaccesskey",
                "testrefreshkeytestrefreshkeytestrefreshkeytestrefreshkeytestrefreshkey",
                1800000L,
                604800000L,
                new Date());
    }

    @DisplayName(value = "액세스 토큰을 발급한다")
    @Test
    void accessToken() throws Exception {
        //given&&when
        String accessToken = jwtUtils.accessToken(email().getValue());
        //then
        System.out.println(accessToken);
        assertThat(accessToken).isNotNull();
        assertThat(accessToken).isInstanceOf(String.class);
    }

    @DisplayName(value = "액세스 토큰을 발급한다")
    @Test
    void refreshToken() throws Exception {
        //given&&when
        String accessToken = jwtUtils.accessToken(email().getValue());
        //then
        System.out.println(accessToken);
        assertThat(accessToken).isNotNull();
        assertThat(accessToken).isInstanceOf(String.class);
    }
}
