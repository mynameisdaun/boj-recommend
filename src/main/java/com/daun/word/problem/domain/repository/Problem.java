package com.daun.word.problem.domain.repository;

import com.daun.word.global.vo.Title;
import com.daun.word.global.vo.URL;
import com.daun.word.problem.domain.repository.vo.Tag;
import com.daun.word.problem.domain.repository.vo.Tier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
