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
        this.title= entry.getKey();
        this.words = entry.getValue();
    }

}
