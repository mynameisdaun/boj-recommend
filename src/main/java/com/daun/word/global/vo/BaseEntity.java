package com.daun.word.global.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@AllArgsConstructor
@Getter
abstract public class BaseEntity {

    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createdAt;

    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date updatedAt;
    @Column(name = "deleted", columnDefinition = "bit default 0")
    private boolean deleted;

    public BaseEntity() {
        Date now = new Date();
        this.createdAt=now;
        this.updatedAt=now;
        this.deleted=false;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }
}
