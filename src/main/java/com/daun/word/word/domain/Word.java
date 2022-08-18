package com.daun.word.word.domain;

import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Word {

    private Integer id;                 // 단어 일련번호(seq)
    private final English english;             // 영어 표기
    private final Korean korean;              // 한글 표기
    private LocalDateTime createdAt;    // 생성 일시
    private LocalDateTime updatedAt;    // 최종 수정 일시

}
