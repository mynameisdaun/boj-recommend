package com.daun.word.domain.quiz.domain;

import com.daun.word.domain.chapter.domain.Chapter;
import com.daun.word.global.Id;
import com.daun.word.domain.quiz.domain.vo.QuizStatus;
import com.daun.word.domain.quiz.domain.vo.QuizType;
import com.daun.word.domain.word.domain.repository.FakeWordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.daun.word.Fixture.Fixture.chapter;
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
        Quiz quiz = quizFactory.generateMultipleChoiceQuiz(word_1(), Id.of(Chapter.class, chapter().getId()));
        //then
        assertThat(quiz).isNotNull();
        assertAll(
                () -> assertThat(quiz.getId()).isNull(),
                () -> assertThat(quiz.getChapterId()).isEqualTo(Id.of(Chapter.class, chapter().getId())),
                () -> assertThat(quiz.getWord()).isEqualTo(word_1()),
                () -> assertThat(quiz.getOptions().size()).isEqualTo(4),
                () -> assertThat(quiz.getOptions()).contains(word_1()),
                () -> assertThat(quiz.getQuizType()).isEqualTo(QuizType.M),
                () -> assertThat(quiz.getQuizStatus()).isEqualTo(QuizStatus.UN_SUBMITTED),
                () -> assertThat(quiz.getSubmission()).isNull()
        );
    }


}
