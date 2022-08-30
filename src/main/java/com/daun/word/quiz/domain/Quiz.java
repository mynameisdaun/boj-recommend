package com.daun.word.quiz.domain;

import com.daun.word.chapter.domain.Chapter;
import com.daun.word.commons.Id;
import com.daun.word.quiz.domain.vo.QuizStatus;
import com.daun.word.quiz.domain.vo.QuizType;
import com.daun.word.word.domain.Word;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@AllArgsConstructor
@Getter
public final class Quiz {
    /* 퀴즈 일련번호 */
    private Integer id;
    /* 챕터 id */
    private Id<Chapter, Integer> chapterId;
    /* 단어 */
    private Word word;
    /* 선택지 */
    private List<Word> options;
    /* 퀴즈 타입 */
    private QuizType quizType;
    /* 퀴즈 제출 상태 */
    private QuizStatus quizStatus;
    /* 제출본 */
    private Word submission;
    /* 데이터 생성일시 */
    private LocalDateTime createdAt;
    /* 데이터 수정일시 */
    private LocalDateTime updatedAt;

    public Quiz(Id<Chapter, Integer> chapterId, Word word, List<Word> options, QuizType quizType) {
        this.chapterId = chapterId;
        this.word = word;
        this.options = options;
        this.quizType = quizType;
        this.quizStatus = QuizStatus.UN_SUBMITTED;
    }

    private Quiz(Integer id, Integer chapterId, Integer word, String options, String quizType, String quizStatus, String submission, LocalDateTime createdAt, LocalDateTime updatedAt) {

    }

    /* 정답 여부 */
    public boolean isCorrect() {
        checkArgument(submission != null, "답안이 제출되기 전에는 채점할 수 없습니다.");
        return word.equals(submission);
    }

    public void submit(Word submission) {
        if (this.quizStatus != QuizStatus.UN_SUBMITTED) {
            throw new IllegalStateException("이미 제츨된 과제는 다시 제출할 수 없습니다.");
        }
        this.submission = submission;
        if (isCorrect()) {
            this.quizStatus = QuizStatus.CORRECT;
            return;
        }
        this.quizStatus = QuizStatus.UN_CORRECT;

    }

    public String getStringOption() {
        return this.options
                .stream()
                .map(w -> w.getId().toString())
                .collect(Collectors.joining(","));
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /* For mybatis */
    private void setChapterId(Integer id) {
        this.chapterId = Id.of(Chapter.class, id);
    }

    private void setQuizStatus(String quizStatus) {
        this.quizStatus = QuizStatus.valueOf(quizStatus);
    }

    private void setQuizType(String quizType) {
        this.quizType = QuizType.valueOf(quizType);
    }
}

