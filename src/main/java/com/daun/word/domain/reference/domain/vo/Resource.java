package com.daun.word.domain.reference.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Resource {

    @Column(name = "resource", nullable = false)
    private String resource;

    public Resource(String resource) {
        this.resource = resource;
    }

    public String getValue() {
        return this.resource;
    }

}
