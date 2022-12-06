package com.daun.word.domain.reference.domain;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.vo.BaseEntity;
import com.daun.word.global.vo.URL;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reference extends BaseEntity {

    @Id
    @Column(name = "reference_id", nullable = false, columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Embedded
    private URL url;

    @Column(name = "allowed", columnDefinition = "bit default 0")
    private boolean allowed;
}
