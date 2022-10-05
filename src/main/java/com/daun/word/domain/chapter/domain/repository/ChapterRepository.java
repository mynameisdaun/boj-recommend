package com.daun.word.domain.chapter.domain.repository;

import com.daun.word.domain.chapter.domain.Chapter;
import com.daun.word.domain.chapter.domain.vo.ChapterWordMapping;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ChapterRepository {

    /* 챕터 저장*/
    int save(Chapter chapter);

    /* 챕터-단어 매핑 저장 */
    int saveChapterWordMapping(ChapterWordMapping chapterWordMapping);
}
