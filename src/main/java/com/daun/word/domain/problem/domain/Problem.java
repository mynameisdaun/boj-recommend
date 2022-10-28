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
    @Column(name="problem_id", nullable = false)
    private Integer id;
    private Title title;
    private URL url;
    private Tier tier;
    @OneToMany(mappedBy = "problem")
    /*다대 다 관계인가 ?*/
    private List<Tag> tags = new ArrayList<Tag>();

    @Column(name = "accepted_user_count", nullable = false, columnDefinition = "int default 0")
    private int acceptedUserCount;
    @Column(name = "recommended_count", nullable = false, columnDefinition = "int default 0")
    private int recommendedCount;

    public Problem(SolvedAcProblemResponse response) {
        this.id = response.getProblemId();
        this.title = new Title(response.getTitleKo());
        this.url = new URL("https://www.acmicpc.net/problem/" + response.getProblemId());
        this.tier = new Tier(response.getLevel());
        this.tags = new ArrayList<>();
        this.acceptedUserCount = response.getAcceptedUserCount();
        this.recommendedCount = 0;
        for (SolvedAcProblemResponse.Tag tag : response.getTags()) {
            this.tags.add(new Tag(tag));
        }
    }
}
