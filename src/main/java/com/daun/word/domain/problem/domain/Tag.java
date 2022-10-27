package com.daun.word.domain.problem.domain;

import com.daun.word.global.infra.solvedac.dto.SolvedAcProblemResponse;
import com.daun.word.global.vo.CreatedAt;
import com.daun.word.global.vo.Title;
import com.daun.word.global.vo.UpdatedAt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "tag")
@Table(name = "tag")
@NoArgsConstructor
@Getter
@ToString
public class Tag {
    @Id
    @Column(name="tag_id", nullable = false)
    private Integer id;

    @Column(name="tag_key", nullable = false)
    private String key;
    private Title title;

    private CreatedAt createdAt;

    private UpdatedAt updatedAt;

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
