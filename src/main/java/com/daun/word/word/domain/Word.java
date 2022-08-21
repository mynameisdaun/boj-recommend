package com.daun.word.word.domain;

import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Word {

    private Integer id;                 // 단어 일련번호(seq)
    private final English english;             // 영어 표기
    private final Korean korean;              // 한글 표기
    private LocalDateTime createdAt;    // 생성 일시
    private LocalDateTime updatedAt;    // 최종 수정 일시

    public Word(Integer id, English english, Korean korean) {
        this.id = id;
        this.english = english;
        this.korean = korean;
    }

    public Word(Integer id, String english, String korean, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.english = new English(english);
        this.korean = new Korean(korean);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
