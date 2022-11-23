package com.daun.word.domain.recommend.dto;

import com.daun.word.domain.recommend.domain.vo.RecommendType;
import com.daun.word.domain.recommend.dto.search.RecommendSearchQuery;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StudyRecommendRequest {

    @NotNull
    private UUID study;

    @NotEmpty
    private String hash;
    @NotNull
    private RecommendType type;
    @NotNull
    private RecommendSearchQuery query;

    public StudyRecommendRequest(String study, String hash, RecommendType type, RecommendSearchQuery query) {
        this.study = UUID.fromString(study);
        this.hash = hash;
        this.type = type;
        this.query = query;
    }
}
