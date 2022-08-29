package com.daun.word.quiz.domain;

import com.daun.word.quiz.domain.vo.QuizStatus;
import com.daun.word.quiz.domain.vo.QuizType;
import com.daun.word.word.domain.repository.FakeWordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.daun.word.Fixture.Fixture.word_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class QuizFactoryTest {

    private QuizFactory quizFactory;

    @BeforeEach
    public void setUp() {
        quizFactory = new QuizFactory(new FakeWordRepository());
    }

    @DisplayName(value = "generateQuiz")
    @Test
    void generateQuiz() throws Exception {
        //given&&when
        Quiz quiz = quizFactory.generateMultipleChoiceQuiz(word_1());
        //then
        assertThat(quiz).isNotNull();
        assertAll(
                () -> assertThat(quiz.getSeq()).isNull(),
                () -> assertThat(quiz.getWord()).isEqualTo(word_1()),
                () -> assertThat(quiz.getQuizType()).isEqualTo(QuizType.M),
                () -> assertThat(quiz.getQuizStatus()).isEqualTo(QuizStatus.UN_SUBMITTED),
                () -> assertThat(quiz.getSubmission()).isNull()
        );
    }




}
