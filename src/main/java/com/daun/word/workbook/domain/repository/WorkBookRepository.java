package com.daun.word.workbook.domain.repository;

import com.daun.word.workbook.domain.WorkBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface WorkBookRepository {

    /* 단어장 저장하기 */
    Integer save(WorkBook workBook);
}
