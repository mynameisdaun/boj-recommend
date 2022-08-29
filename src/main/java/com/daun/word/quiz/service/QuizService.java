package com.daun.word.quiz.service;

import com.daun.word.quiz.domain.repository.QuizRepository;
import com.daun.word.word.domain.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;

}
