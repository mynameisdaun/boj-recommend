package com.daun.word.quiz.domain.vo;

public enum QuizStatus {

    CORRECT("CORRECT"),           // 정답
    UN_CORRECT("UN_CORRECT"),     // 오답
    UN_SUBMITTED("UN_SUBMITTED"); // 미 제출

    private final String value;

    QuizStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static QuizStatus of (String value) {
        for (QuizStatus status : QuizStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }

}
