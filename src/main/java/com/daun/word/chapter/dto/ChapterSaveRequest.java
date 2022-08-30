package com.daun.word.chapter.dto;

import com.daun.word.word.domain.Word;
import com.daun.word.workbook.domain.vo.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterSaveRequest {

    private Integer workBookId;
    private Title title;
    private List<Word> words;

    public ChapterSaveRequest(Title title, List<Word> words) {
        this.title = title;
        this.words = words;
    }

    public ChapterSaveRequest(Map.Entry<Title, List<Word>> entry) {
        this.title = entry.getKey();
        this.words = entry.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChapterSaveRequest that = (ChapterSaveRequest) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return words != null ? words.equals(that.words) : that.words == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (words != null ? words.hashCode() : 0);
        return result;
    }
}
