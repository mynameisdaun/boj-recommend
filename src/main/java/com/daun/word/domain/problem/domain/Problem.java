package com.daun.word.domain.problem.domain;

import com.daun.word.global.vo.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "accepted_user_count", nullable = false, columnDefinition = "int default 0")
    private int acceptedUserCount;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<ProblemTag> problemTags = new ArrayList<>();

    public Problem(Integer id, Title title, URL url, Tier tier, int acceptedUserCount) {
        super();
        this.id = id;
        this.title = title;
        this.url = url;
        this.tier = tier;
        this.acceptedUserCount = acceptedUserCount;
    }

    public void addTags(List<ProblemTag> tags) {
        for (ProblemTag tag : tags) {
            if (!this.problemTags.contains(tag)) {
                this.problemTags.add(tag);
            }
        }
    }
}
