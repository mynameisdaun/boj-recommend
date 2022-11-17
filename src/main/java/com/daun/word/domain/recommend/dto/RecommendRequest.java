package com.daun.word.domain.recommend.dto;

import com.daun.word.domain.recommend.domain.vo.RecommendType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendRequest {

    private String email;
    private RecommendType type;
    private RecommendSearchQuery query;

    public RecommendRequest(String email, RecommendType type, RecommendSearchQuery query) {
        this.email = email;
        this.type = type;
        this.query = query;
    }

}
