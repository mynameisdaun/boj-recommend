package com.daun.word.chapter.dto;

import com.daun.word.workbook.domain.vo.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterSaveRequest {

    private Integer workBookId;
    private Title title;
    private List<String> words;

    public ChapterSaveRequest(Title title, List<String> words) {
        this.title = title;
        this.words = words;
    }

}
