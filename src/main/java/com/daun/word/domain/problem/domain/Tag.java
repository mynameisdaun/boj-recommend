package com.daun.word.domain.problem.domain;

import com.daun.word.global.infra.solvedac.dto.SolvedAcProblemResponse;
import com.daun.word.global.vo.BaseEntity;
import com.daun.word.global.vo.Title;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tag")
@Table(name = "tag")
@Getter
@ToString
public class Tag extends BaseEntity {
    @Id
    @Column(name="tag_id", nullable = false)
    private Long id;

    @Column(name="tag_key", nullable = false)
    private String key;
    private Title title;

    @OneToMany(mappedBy = "tag")
    private List<ProblemTag> problemTags;

    public Tag(Long id, String key, Title title) {
        this.id = id;
        this.key = key;
        this.title = title;
    }

    protected Tag() {
    }

    public Tag(SolvedAcProblemResponse.Tag tag) {
        this.id = Long.valueOf(tag.getBojTagId());
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
