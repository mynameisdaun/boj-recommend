package com.daun.word.workbook.domain;

import com.daun.word.workbook.domain.vo.Author;
import com.daun.word.workbook.domain.vo.Description;
import com.daun.word.workbook.domain.vo.Title;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@ToString
public class WorkBook {
    private final Logger logger = LoggerFactory.getLogger(WorkBook.class);

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
