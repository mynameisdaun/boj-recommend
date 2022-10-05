package com.daun.word.domain.quiz.domain.repository;

import com.daun.word.domain.chapter.domain.Chapter;
import com.daun.word.global.Id;
import com.daun.word.domain.quiz.domain.Quiz;
import com.daun.word.domain.quiz.domain.QuizFactory;
import com.daun.word.domain.quiz.domain.vo.QuizStatus;
import com.daun.word.domain.word.domain.repository.FakeWordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.NoSuchElementException;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    private QuizFactory quizFactory;

    @BeforeEach
    void setUp() {
        quizFactory = new QuizFactory(new FakeWordRepository());
    }

    @DisplayName(value = "퀴즈 아이디로 퀴즈를 조회한다")
    @Test
    void findById() throws Exception {
        //given
        Quiz quiz = quiz_correct();
        //when
        Quiz find = quizRepository.findById(Id.of(Quiz.class, quiz.getId())).orElseThrow(NoSuchElementException::new);
        //then
        assertThat(find).isNotNull();
        assertAll(
                () -> assertThat(find.getId()).isEqualTo(quiz.getId()),
                () -> assertThat(find.getChapterId()).isEqualTo(quiz.getChapterId()),
                () -> assertThat(find.getWord()).isEqualTo(quiz.getWord()),
                () -> assertThat(find.getOptions().size()).isEqualTo(quiz.getOptions().size()),
                () -> assertThat(find.getQuizType()).isEqualTo(quiz.getQuizType()),
                () -> assertThat(find.getQuizStatus()).isEqualTo(quiz.getQuizStatus()),
                () -> assertThat(find.getSubmission()).isEqualTo(quiz.getSubmission())
        );
    }

    @DisplayName(value = "퀴즈를 저장한다.")
    @Test
    void save() throws Exception {
        //given
        Quiz quiz = quizFactory.generateMultipleChoiceQuiz(word_1(), Id.of(Chapter.class, 1));
        //when
        int saved = quizRepository.save(quiz);
        //then
        assertThat(saved).isOne();
        assertAll(
                () -> assertThat(quiz.getId()).isNotNull(),
                () -> assertThat(quiz.getCreatedAt()).isNotNull(),
                () -> assertThat(quiz.getUpdatedAt()).isNotNull()
        );
    }

    @DisplayName(value = "퀴즈를 업데이트 한다.")
    @Test
    void update() throws Exception {
        //given
        Quiz quiz = quiz_un_submitted();
        quiz.submit(quiz.getWord());
        //when
        System.out.println(quiz.getId());
        int saved = quizRepository.update(quiz);
        //then
        assertThat(saved).isOne();
        assertAll(
                () -> assertThat(quiz.getSubmission()).isEqualTo(quiz.getWord()),
                () -> assertThat(quiz.getQuizStatus()).isEqualTo(QuizStatus.CORRECT),
                () -> assertThat(quiz.getUpdatedAt()).isNotNull()
        );
    }

}
