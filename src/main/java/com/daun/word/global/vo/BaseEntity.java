package com.daun.word.global.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
abstract public class BaseEntity {

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;
    @Column(name = "delete_yn", nullable = false)
    private boolean deleteYn;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        createdAt = now;
        updatedAt = now;
        deleteYn = false;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }
}
