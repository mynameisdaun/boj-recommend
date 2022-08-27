package com.daun.word.word.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.function.Consumer;

@Getter
@ToString
public class Words {

    private final List<Word> words;

    public Words(List<Word> words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("단어는 하나 이상이어야 합니다");
        }
        this.words = words;
    }

    public void forEach(Consumer<? super Word> action) {
        this.words.forEach(action);
    }
}
