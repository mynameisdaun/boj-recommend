package com.daun.word.chapter.domain;

import com.daun.word.word.domain.Words;
import com.daun.word.global.vo.Title;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Chapter {
    private final Title title;
    private final Integer workBookId;
    private final Words words;
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Chapter(Integer workBookId, Title title, Words words) {
        this.workBookId = workBookId;
        this.title = title;
        this.words = words;
    }

    public Chapter(Integer id, Title title, Integer workBookId, Words words) {
        this.id = id;
        this.title = title;
        this.workBookId = workBookId;
        this.words = words;
    }

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
