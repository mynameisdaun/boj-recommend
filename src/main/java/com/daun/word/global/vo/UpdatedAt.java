package com.daun.word.global.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class UpdatedAt {

    @Column(name = "updated_at",  nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

    public UpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
