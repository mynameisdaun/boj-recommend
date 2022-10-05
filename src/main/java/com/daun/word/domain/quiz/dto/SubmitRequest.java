package com.daun.word.domain.quiz.dto;

import com.daun.word.global.Id;
import com.daun.word.domain.quiz.domain.Quiz;
import com.daun.word.domain.word.domain.Word;
import lombok.Data;

@Data
public class SubmitRequest {
    private final Id<Quiz, Integer> quiz;
    private final Word submit;
}
