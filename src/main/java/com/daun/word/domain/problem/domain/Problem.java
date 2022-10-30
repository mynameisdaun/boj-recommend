package com.daun.word.domain.problem.domain;

import com.daun.word.global.infra.solvedac.dto.SolvedAcProblemResponse;
import com.daun.word.global.vo.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "problem")
@Table(name = "problem")
@NoArgsConstructor
@Getter
@ToString
public class Problem extends BaseEntity {

    @Id
    @Column(name = "problem_id", nullable = false)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private URL url;

    @Embedded
    private Tier tier;

    @OneToMany(mappedBy = "problem")
    private List<ProblemTag> problemTags = new ArrayList<>();

    public Problem(Long id, Title title, URL url, Tier tier) {
        super();
        this.id = id;
        this.title = title;
        this.url = url;
        this.tier = tier;
        this.problemTags = null;
    }

    public void addTags(List<ProblemTag> tags) {
        for (ProblemTag tag : tags) {
            if (!this.problemTags.contains(tag)) {
                this.problemTags.add(tag);
            }
        }
    }
}
