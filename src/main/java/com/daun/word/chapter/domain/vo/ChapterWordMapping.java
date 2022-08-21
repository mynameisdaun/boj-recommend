package com.daun.word.chapter.domain.vo;

import com.daun.word.chapter.domain.Chapter;
import com.daun.word.word.domain.Word;
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
}
