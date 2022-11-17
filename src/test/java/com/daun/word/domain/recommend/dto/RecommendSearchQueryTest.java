package com.daun.word.domain.recommend.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RecommendSearchQueryTest {

    @DisplayName(value = "최대 티어는 최소 티어보다 같거나 커야 한다")
    @Test
    void tier() throws Exception {
        //given
        int minTier = 3;
        int maxTier = 1;
        //when&&then
        assertThatThrownBy(() -> new RecommendSearchQuery(minTier, maxTier))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("최대 티어는 최소 티어보다 같거나 커야 합니다");
    }

}