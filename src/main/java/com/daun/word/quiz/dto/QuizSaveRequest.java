package com.daun.word.quiz.dto;

import com.daun.word.chapter.domain.Chapter;
import com.daun.word.global.Id;
import com.daun.word.word.domain.Word;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizSaveRequest {
    private Id<Chapter, Integer> chapterId;
    private Word word;
}
