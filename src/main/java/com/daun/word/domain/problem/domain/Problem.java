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
    private Integer id;

    @Embedded
    private Title title;

    @Embedded
    private URL url;

    @Embedded
    private Tier tier;

    @OneToMany(mappedBy = "problem")
    private List<ProblemTag> problemTags;

    @Column(name = "accepted_user_count", nullable = false, columnDefinition = "int default 0")
    private int acceptedUserCount;

    @Column(name = "recommended_count", nullable = false, columnDefinition = "int default 0")
    private int recommendedCount;

    public Problem(Integer id, Title title, URL url, Tier tier, List<Tag> problemTags) {
        super();
        this.id = id;
        this.title = title;
        this.url = url;
        this.tier = tier;
        this.problemTags = null;
        //TODO: no ..
        this.acceptedUserCount = 0;
        this.recommendedCount = 0;
    }
}
