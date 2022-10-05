package com.daun.word.domain.workbook.dto;

import com.daun.word.domain.chapter.dto.ChapterSaveResponse;
import com.daun.word.domain.workbook.domain.WorkBook;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WorkBookSaveResponse {
    private final WorkBook workBook;
    private final List<ChapterSaveResponse> chapters;
}
