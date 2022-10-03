package com.daun.word.chapter.dto;

import com.daun.word.quiz.dto.QuizResponse;
import com.daun.word.word.domain.Words;
import com.daun.word.global.vo.Title;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChapterSaveResponse {
    private final Title title;
    private final Words words;
    private final List<QuizResponse> quizs;
}
