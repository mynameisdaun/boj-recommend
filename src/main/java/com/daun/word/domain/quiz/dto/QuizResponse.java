package com.daun.word.domain.quiz.dto;

import com.daun.word.domain.quiz.domain.Quiz;
import lombok.Data;

@Data
public class QuizResponse {

    private final Quiz quiz;

    public QuizResponse(Quiz quiz) {
        this.quiz = quiz;
    }
}
