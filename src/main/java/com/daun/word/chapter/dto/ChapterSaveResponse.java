package com.daun.word.chapter.dto;

import com.daun.word.word.domain.Words;
import com.daun.word.workbook.domain.vo.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChapterSaveResponse {

    private final Title title;
    private final Words words;
}
