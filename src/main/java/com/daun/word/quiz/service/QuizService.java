package com.daun.word.quiz.service;

import com.daun.word.quiz.domain.Quiz;
import com.daun.word.quiz.domain.QuizFactory;
import com.daun.word.quiz.domain.repository.QuizRepository;
import com.daun.word.quiz.dto.QuizResponse;
import com.daun.word.quiz.dto.QuizSaveRequest;
import com.daun.word.quiz.dto.SubmitRequest;
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
