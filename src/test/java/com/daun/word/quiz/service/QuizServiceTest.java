package com.daun.word.quiz.service;

import com.daun.word.chapter.domain.Chapter;
import com.daun.word.commons.Id;
import com.daun.word.quiz.domain.Quiz;
import com.daun.word.quiz.domain.QuizFactory;
import com.daun.word.quiz.domain.repository.FakeQuizRepository;
import com.daun.word.quiz.domain.vo.QuizStatus;
import com.daun.word.quiz.dto.QuizResponse;
import com.daun.word.quiz.dto.QuizSaveRequest;
import com.daun.word.quiz.dto.SubmitRequest;
import com.daun.word.word.domain.repository.FakeWordRepository;
import com.daun.word.word.domain.repository.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class QuizServiceTest {

    private QuizService quizService;

    @BeforeEach
    void setUp() {
        WordRepository wordRepository = new FakeWordRepository();
        this.quizService = new QuizService(new FakeQuizRepository(wordRepository), new QuizFactory(wordRepository));
    }

    @DisplayName(value = "퀴즈를 저장한다")
    @Test
    void save() throws Exception {
        //given
        QuizSaveRequest request = new QuizSaveRequest(Id.of(Chapter.class, chapter().getId()), word_1());
        //when
        QuizResponse response = quizService.save(request);
        //then
        assertThat(response).isNotNull();
        assertAll(
                () -> assertThat(response.getQuiz().getId()).isNotNull(),
                () -> assertThat(response.getQuiz().getOptions().size()).isEqualTo(4),
                () -> assertThat(response.getQuiz().getQuizStatus()).isEqualTo(QuizStatus.UN_SUBMITTED),
                () -> assertThat(response.getQuiz().getSubmission()).isNull()
        );
    }

    @DisplayName(value = "퀴즈를 제출한다 - 정답")
    @Test
    void submit_correct() throws Exception {
        //given
        SubmitRequest submitRequest = new SubmitRequest(Id.of(Quiz.class, quiz_un_submitted().getId()), word_1());
        //when
        QuizResponse response = quizService.submit(submitRequest);
        //then
        assertThat(response).isNotNull();
        assertAll(
                () -> assertThat(response.getQuiz()).isNotNull(),
                () -> assertThat(response.getQuiz().getQuizStatus()).isEqualTo(QuizStatus.CORRECT),
                () -> assertThat(response.getQuiz().getSubmission()).isEqualTo(word_1())
        );
    }

    @DisplayName(value = "퀴즈를 제출한다-오답")
    @Test
    void submit_un_correct() throws Exception {
        //given
        SubmitRequest submitRequest = new SubmitRequest(Id.of(Quiz.class, quiz_un_submitted().getId()), word_2());
        //when
        QuizResponse response = quizService.submit(submitRequest);
        //then
        assertThat(response).isNotNull();
        assertAll(
                () -> assertThat(response.getQuiz()).isNotNull(),
                () -> assertThat(response.getQuiz().getQuizStatus()).isEqualTo(QuizStatus.UN_CORRECT),
                () -> assertThat(response.getQuiz().getSubmission()).isEqualTo(word_2())
        );
    }

    @DisplayName(value = "이미 제출한 퀴즈는 제출할 수 없다")
    @Test
    void submit_already_submitted() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> quizService.submit(new SubmitRequest(Id.of(Quiz.class, quiz_correct().getId()), word_2())))
                .isInstanceOf(IllegalStateException.class);
    }
}
