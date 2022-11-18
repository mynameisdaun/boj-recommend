package com.daun.word.global.infra.solvedac.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@ToString
public class TierCounts {
    private final List<TierCount> counts;

    public TierCount get(final int n) {
        for (TierCount tc : counts) {
            if (tc.getTier().getLevel() == n) {
                return tc;
            }
        }
        throw new IllegalStateException("");
    }
}
