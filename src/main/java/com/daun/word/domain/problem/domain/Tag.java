package com.daun.word.domain.problem.domain;

import com.daun.word.global.infra.solvedac.dto.SolvedAcProblemResponse;
import com.daun.word.global.vo.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "tag")
@Table(name = "tag")
@Getter
@ToString
public class Tag extends BaseEntity {
    @Id
    @Column(name="tag_id", nullable = false)
    private Integer id;

    @Column(name="tag_key", nullable = false)
    private String key;
    private Title title;

    public Tag(Integer id, String key, Title title) {
        this.id = id;
        this.key = key;
        this.title = title;
    }

    protected Tag() {
    }

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
