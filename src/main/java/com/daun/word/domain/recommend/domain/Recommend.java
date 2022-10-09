package com.daun.word.domain.recommend.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
public class Recommend {
    private Integer id;
    private final Problem problem;
    private final Member member;
    private int recommendedCount;
    private String chooseYn;
    private LocalDateTime chooseDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void choose() {
        this.chooseYn = "Y";
        this.chooseDateTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recommend recommend = (Recommend) o;

        return id == recommend.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
