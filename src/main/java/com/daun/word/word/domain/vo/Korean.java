package com.daun.word.word.domain.vo;

import com.daun.word.utils.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import static com.daun.word.utils.StringUtils.*;

@Getter
@EqualsAndHashCode
@ToString
public class Korean {

    private final String korean;

    public Korean(String korean) {
        if(isNullOrBlank(korean)) {
            throw new IllegalArgumentException("단어의 한글 표기는 한글자 이상이어야 합니다.");
        }
        this.korean = korean;
    }

    public String getValue() {
        return this.korean;
    }

}
