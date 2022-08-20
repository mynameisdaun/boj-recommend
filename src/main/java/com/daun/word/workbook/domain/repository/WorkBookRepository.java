package com.daun.word.workbook.domain.repository;

import com.daun.word.workbook.domain.WorkBook;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkBookRepository {

    /* 단어장 저장하기 */
    Integer save(WorkBook workBook);
}
