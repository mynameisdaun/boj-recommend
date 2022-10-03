package com.daun.word.problem.domain.vo;

import com.daun.word.global.vo.Title;
import com.daun.word.infra.solvedac.dto.SolvedAcProblemResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
public class Tag {
    private final Integer id;
    private final String key;
    private final Title title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Tag(SolvedAcProblemResponse.Tag tag) {
        this.id = tag.getBojTagId();
        this.key = tag.getKey();
        this.title = new Title(
                tag.getDisplayNames()
                        .stream()
                        .filter(a -> a.getLanguage().equals("ko"))
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new)
                        .getName()
        );
    }
}
