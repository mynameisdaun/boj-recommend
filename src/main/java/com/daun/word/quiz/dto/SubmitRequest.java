package com.daun.word.quiz.dto;

import com.daun.word.commons.Id;
import com.daun.word.quiz.domain.Quiz;
import com.daun.word.word.domain.Word;
import lombok.Data;

@Data
public class SubmitRequest {
    private final Id<Quiz, Integer> quiz;
    private final Word submit;
}
