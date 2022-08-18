package com.daun.word.word.dto;

import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import lombok.Data;

@Data
public class WordSaveRequest {

    private final English english;
    private final Korean korean;

    public WordSaveRequest(String english, String korean) {
        this.english = new English(english);
        this.korean = new Korean(korean);
    }

}
