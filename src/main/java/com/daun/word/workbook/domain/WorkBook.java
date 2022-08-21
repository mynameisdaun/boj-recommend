package com.daun.word.workbook.domain;

import com.daun.word.workbook.domain.vo.Author;
import com.daun.word.workbook.domain.vo.Description;
import com.daun.word.workbook.domain.vo.Title;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class WorkBook {

    private Integer id;
    private final Title title;
    private final Author author;
    private final Description description;
    private final String coverImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public WorkBook(Integer id, Title title, Author author, Description description, String coverImageUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.coverImageUrl = coverImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkBook workBook = (WorkBook) o;

        return id != null ? id.equals(workBook.id) : workBook.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
