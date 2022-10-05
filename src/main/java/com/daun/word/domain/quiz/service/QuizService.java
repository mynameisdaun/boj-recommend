package com.daun.word.domain.quiz.service;

import com.daun.word.domain.quiz.domain.Quiz;
import com.daun.word.domain.quiz.domain.QuizFactory;
import com.daun.word.domain.quiz.domain.repository.QuizRepository;
import com.daun.word.domain.quiz.dto.QuizResponse;
import com.daun.word.domain.quiz.dto.QuizSaveRequest;
import com.daun.word.domain.quiz.dto.SubmitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;

    private final QuizFactory quizFactory;

    public QuizResponse save(QuizSaveRequest request) {
        Quiz quiz = quizFactory.generateMultipleChoiceQuiz(request.getWord(), request.getChapterId());
        quizRepository.save(quiz);
        return new QuizResponse(quiz);
    }

    public QuizResponse submit(SubmitRequest request) {
        Quiz quiz = quizRepository.findById(request.getQuiz())
                .orElseThrow(NoSuchElementException::new);
        quiz.submit(request.getSubmit());
        quizRepository.update(quiz);
        return new QuizResponse(quiz);
    }
}
