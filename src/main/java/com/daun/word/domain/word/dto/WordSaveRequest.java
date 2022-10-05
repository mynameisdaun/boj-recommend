package com.daun.word.domain.word.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.word.domain.vo.English;
import com.daun.word.domain.word.domain.vo.Korean;
import lombok.Data;

@Data
public class WordSaveRequest {

    private final English english;
    private final Korean korean;
    private final Email createdBy;

    //TODO: 이후 토큰으로 대체한다
    public WordSaveRequest(String english, String korean, String createdBy) {
        this.english = new English(english);
        this.korean = new Korean(korean);
        this.createdBy = new Email(createdBy);
    }

}
