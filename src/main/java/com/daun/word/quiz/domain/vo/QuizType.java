package com.daun.word.quiz.domain.vo;

public enum QuizType {
    M("MULTIPLE_CHOICE"), // multipleChoice 객관식
    D("DICTATION"); // dictation 받아쓰기

    private final String value;

    QuizType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static QuizType of(String value) {
        for (QuizType type : QuizType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
        //TODO: throw error?
    }
}
