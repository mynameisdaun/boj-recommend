package com.daun.word.word.domain;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
public class Word {

    private Integer id;                 // 단어 일련번호(seq)
    private final English english;             // 영어 표기
    private final Korean korean;              // 한글 표기
    private final Email createdBy;
    private LocalDateTime createdAt;    // 생성 일시
    private LocalDateTime updatedAt;    // 최종 수정 일시

    public Word(Integer id, English english, Korean korean, Email createdBy) {
        this.id = id;
        this.english = english;
        this.korean = korean;
        this.createdBy = createdBy;
    }

    public Word(Integer id, String english, String korean, String createdBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.english = new English(english);
        this.korean = new Korean(korean);
        this.createdBy = new Email(createdBy);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (id != null ? !id.equals(word.id) : word.id != null) return false;
        if (english != null ? !english.equals(word.english) : word.english != null) return false;
        if (korean != null ? !korean.equals(word.korean) : word.korean != null) return false;
        return createdBy != null ? createdBy.equals(word.createdBy) : word.createdBy == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (english != null ? english.hashCode() : 0);
        result = 31 * result + (korean != null ? korean.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        return result;
    }
}
