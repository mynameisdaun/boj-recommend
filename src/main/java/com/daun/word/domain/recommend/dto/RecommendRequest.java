package com.daun.word.domain.recommend.dto;

import com.daun.word.global.vo.Tier;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecommendRequest {
    private String email;

    private String recommendType;

    private Tier min;

    private Tier max;
}
