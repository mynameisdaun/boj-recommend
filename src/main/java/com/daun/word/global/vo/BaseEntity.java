package com.daun.word.global.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@MappedSuperclass
@AllArgsConstructor @NoArgsConstructor @Getter
abstract public class BaseEntity {

    @Embedded private CreatedAt createdAt;

    @Embedded private UpdatedAt updatedAt;

    @Column(length = 1, columnDefinition = "char(1) default 'N'")
    @Enumerated(value = EnumType.STRING)
    private YesNo deleteYn;
}
