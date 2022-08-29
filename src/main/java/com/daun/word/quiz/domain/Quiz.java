package com.daun.word.quiz.domain;

import com.daun.word.commons.Id;
import com.daun.word.quiz.domain.vo.QuizStatus;
import com.daun.word.quiz.domain.vo.QuizType;
import com.daun.word.word.domain.Word;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.common.base.Preconditions.checkArgument;

@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public final class Quiz {
    /* 퀴즈 일련번호 */
    private Long seq;
    /* 단어 */
    private final Word word;
    /* 선택지 */
    private final List<Word> options;
    /* 퀴즈 타입 */
    private final QuizType quizType;
    /* 퀴즈 제출 상태 */
    private final QuizStatus quizStatus;
    /* 제출본 */
    private Word submission;

    /* 정답 여부 */
    public boolean isCorrect() {
        checkArgument(submission != null, "답안이 제출되기 전에는 채점할 수 없습니다.");
        return word == submission;
    }


}
