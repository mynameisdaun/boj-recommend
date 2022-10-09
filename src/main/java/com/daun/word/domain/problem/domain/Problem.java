package com.daun.word.domain.problem.domain;

import com.daun.word.domain.problem.domain.vo.Tag;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblemResponse;
import com.daun.word.global.vo.Tier;
import com.daun.word.global.vo.Title;
import com.daun.word.global.vo.URL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
public class Problem {

    private final Integer id;
    private final Title title;
    private final URL url;
    private final Tier tier;
    private final List<Tag> tags;
    private final int acceptedUserCount;
    private final int recommendedCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Problem(SolvedAcProblemResponse response) {
        this.id = response.getProblemId();
        this.title = new Title(response.getTitleKo());
        this.url = new URL("https://www.acmicpc.net/problem/" + response.getProblemId());
        this.tier = new Tier(response.getLevel());
        this.tags = new ArrayList<>();
        this.acceptedUserCount = response.getAcceptedUserCount();
        this.recommendedCount = 0;
        for (SolvedAcProblemResponse.Tag tag : response.getTags()) {
            this.tags.add(new Tag(tag));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Problem problem = (Problem) o;

        return id != null ? id.equals(problem.id) : problem.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
