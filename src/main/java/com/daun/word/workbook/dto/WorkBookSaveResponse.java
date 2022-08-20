package com.daun.word.workbook.dto;

import com.daun.word.chapter.domain.Chapters;
import com.daun.word.chapter.dto.ChapterSaveResponse;
import com.daun.word.workbook.domain.WorkBook;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WorkBookSaveResponse {
    private final WorkBook workBook;
    private final List<ChapterSaveResponse> chapters;
}
