package com.daun.word.domain.reference.domain.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.HashMap;
import java.util.Map;

@Embeddable
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefDetail {
    //TODO: DB에서 꺼내올 수 있게
    private static Map<Integer, String> map = new HashMap<>();

    @Column(name = "positive", columnDefinition = "bit default 0")
    private boolean positive;

    @Column(name = "detail_index")
    private int detailIndex;
}
