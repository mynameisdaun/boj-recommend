package com.daun.word.domain.problem.domain;

import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;
import com.daun.word.global.vo.BaseEntity;
import com.daun.word.global.vo.Title;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "tag")
@Table(name = "tag")
@Getter
@ToString
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Tag extends BaseEntity {
    @Id
    @Column(name = "tag_id", nullable = false)
    private Integer id;

    @Column(name = "tag_key", nullable = false)
    private String key;
    private Title title;

    public Tag(Integer id, String key, Title title) {
        super();
        this.id = id;
        this.key = key;
        this.title = title;
    }

    public Tag(SolvedAcProblem.Tag tag) {
        this(tag.getBojTagId(),tag.getKey(),new Title(
                tag.getDisplayNames()
                        .stream()
                        .filter(a -> a.getLanguage().equals("ko") || a.getLanguage().equals("en"))
                        .sorted((a,b) -> b.getLanguage().compareTo(a.getLanguage()))
                        .findFirst()
                        .get().getName()
        ));
    }
}
