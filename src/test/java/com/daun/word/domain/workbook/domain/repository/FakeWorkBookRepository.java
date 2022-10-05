package com.daun.word.domain.workbook.domain.repository;

import com.daun.word.domain.workbook.domain.WorkBook;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.daun.word.Fixture.Fixture.workbook;

public class FakeWorkBookRepository implements WorkBookRepository {

    private final Map<Integer, WorkBook> workBookTable;

    /* init */
    public FakeWorkBookRepository() {
        this.workBookTable = new HashMap<>();
        workBookTable.put(workbook().getId(), workbook());
    }

    @Override
    public Integer save(WorkBook workBook) {
        workBook.setId(workBookTable.size());
        workBook.setCreatedAt(LocalDateTime.now());
        workBook.setUpdatedAt(LocalDateTime.now());
        workBookTable.put(workBookTable.size(), workBook);
        return 1;
    }
}
