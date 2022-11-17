package com.daun.word.domain.problem.domain;

import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;
import com.daun.word.global.vo.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "problem")
@Table(name = "problem")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id", callSuper = false) @ToString
public class Problem extends BaseEntity {

    @Id
    @Column(name = "problem_id", nullable = false)
    private Integer id;

    @Embedded
    private Title title;

    @Embedded
    private URL url;

    @Embedded
    private Tier tier;

    @Column(name = "accepted_user_count", nullable = false, columnDefinition = "int default 0")
    private int acceptedUserCount;

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "problem", cascade = CascadeType.PERSIST)
    private List<ProblemTag> problemTags = new ArrayList<>();

    public Problem(Integer id, Title title, URL url, Tier tier, int acceptedUserCount) {
        super();
        this.id = id;
        this.title = title;
        this.url = url;
        this.tier = tier;
        this.acceptedUserCount = acceptedUserCount;
    }

    public Problem(SolvedAcProblem solvedAcProblem) {
        this(solvedAcProblem.getProblemId(), new Title(solvedAcProblem.getTitleKo()), new URL(solvedAcProblem.getProblemId()), new Tier(solvedAcProblem.getLevel()), solvedAcProblem.getAcceptedUserCount());
    }

    public void addTags(List<ProblemTag> tags) {
        for (ProblemTag tag : tags) {
            if (!this.problemTags.contains(tag)) {
                this.problemTags.add(tag);
            }
        }
    }
}
