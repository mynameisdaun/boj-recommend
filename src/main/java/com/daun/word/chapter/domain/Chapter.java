package com.daun.word.chapter.domain;

import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.Words;
import com.daun.word.workbook.domain.vo.Title;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Chapter {
    private Integer id;
    private Title title;
    private Integer workBookId;
    private Words words;
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
}
