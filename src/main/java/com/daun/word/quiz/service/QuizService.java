package com.daun.word.quiz.service;

import com.daun.word.commons.Id;
import com.daun.word.quiz.domain.repository.QuizRepository;
import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final WordRepository wordRepository;
    private final int numberOfOptions = 4;

}
