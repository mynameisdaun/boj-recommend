package com.daun.word.domain.problem.dto;

import com.daun.word.domain.problem.domain.ProblemTag;
import com.daun.word.domain.problem.domain.Tag;

import com.google.common.base.Preconditions;
import lombok.Data;

@Data
public class TagDTO {
    private final String key;
    private final String title;

    public TagDTO(String key, String title) {
        Preconditions.checkArgument(key != null && title != null);
        this.key = key;
        this.title = title;
    }

    public TagDTO(Tag tag) {
        this(tag.getKey(), tag.getTitle().getValue());
    }

    public TagDTO(ProblemTag problemTag) {
        this(problemTag.getTag());
    }
}
