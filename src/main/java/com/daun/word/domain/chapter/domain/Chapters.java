package com.daun.word.domain.chapter.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Consumer;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Chapters {

    private List<Chapter> chapters;

    public Chapters(List<Chapter> chapters) {
        if (chapters == null || chapters.isEmpty()) {
            throw new IllegalArgumentException("챕터는 하나 이상이어야 합니다");
        }
        this.chapters = chapters;
    }

    public Chapter get(int index) {
        return chapters.get(index);
    }

    public boolean isEmpty() {
        return chapters.isEmpty();
    }

    public int size() {
        return chapters.size();
    }

    public void forEach(Consumer<? super Chapter> action) {
        this.chapters.forEach(action);
    }

    public List<Chapter> getValue() {
        return this.chapters;
    }
}
