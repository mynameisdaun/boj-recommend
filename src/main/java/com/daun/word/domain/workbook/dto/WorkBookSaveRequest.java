package com.daun.word.domain.workbook.dto;

import com.daun.word.domain.chapter.dto.ChapterSaveRequest;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.workbook.domain.WorkBook;
import com.daun.word.domain.workbook.domain.vo.Author;
import com.daun.word.domain.workbook.domain.vo.Description;
import com.daun.word.global.vo.Title;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class WorkBookSaveRequest {
    private Title title;
    private Author author;
    private Description description;
    private Email createdBy;
    private List<ChapterSaveRequest> chapters;
    private String coverImageUrl;

    public WorkBookSaveRequest(Title title, Author author, Description description, Email createdBy, List<ChapterSaveRequest> chapters, String coverImageUrl) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.createdBy = createdBy;
        this.chapters = chapters;
        this.coverImageUrl = coverImageUrl;
    }

    public WorkBook toWorkBook() {
        return new WorkBook(this.title, this.author, this.description, this.coverImageUrl);
    }
}
