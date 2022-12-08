package com.daun.word.domain.reference.domain;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.reference.domain.vo.RefDetail;
import com.daun.word.domain.reference.domain.vo.ReferenceType;
import com.daun.word.domain.reference.domain.vo.Resource;
import com.daun.word.global.vo.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

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

    @Enumerated(EnumType.STRING)
    private ReferenceType type;

    @Embedded
    private Resource resource;

    @Embedded
    private RefDetail detail;

    @Column(name = "allowed", columnDefinition = "bit default 0")
    private boolean allowed;

}
