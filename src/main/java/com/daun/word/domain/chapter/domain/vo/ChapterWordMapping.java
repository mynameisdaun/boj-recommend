package com.daun.word.domain.chapter.domain.vo;

import com.daun.word.domain.chapter.domain.Chapter;
import com.daun.word.domain.word.domain.Word;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ChapterWordMapping {
    private final Chapter chapter;
    private final Word word;
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
