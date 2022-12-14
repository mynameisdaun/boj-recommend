package com.daun.word.global.infra.solvedac.dto;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.GlobalId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
public final class ProblemSearchResponse {
    private int count;
    private Item[] items;

    public List<Integer> toIdList() {
        return stream(this.items)
                .map(Item::getProblemId)
                .collect(toList());
    }

    @Data
    @NoArgsConstructor
    public static class Item {
        private int problemId;
        private String titleKo;
        private boolean isSolvable;
        private boolean isPartial;
        private int acceptedUserCount;
        private int level;
        private int votedUserCount;
        private boolean isLevelLocked;
        private double averageTries;
    }

}
