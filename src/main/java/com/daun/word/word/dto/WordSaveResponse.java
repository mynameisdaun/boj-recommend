package com.daun.word.word.dto;

import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import lombok.Data;

@Data
public class WordSaveResponse {

    private final String english;
    private final String korean;

    public WordSaveResponse(English english, Korean korean) {
        this.english = english.getValue();
        this.korean = korean.getValue();
    }

    public static WordSaveResponse fromWord (Word word) {
        return new WordSaveResponse(word.getEnglish(), word.getKorean());
    }
}
