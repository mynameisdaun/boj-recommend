package com.daun.word.domain.recommend.dto;

import com.daun.word.domain.recommend.domain.vo.RecommendType;
import com.daun.word.global.vo.Tier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecommendRequest {

    private String email;
    private RecommendType recommendType;
    private RecommendSearchQuery query;

    public RecommendRequest(String email, RecommendType recommendType, RecommendSearchQuery query) {
        this.email = email;
        this.recommendType = recommendType;
        this.query = query;
    }

}
