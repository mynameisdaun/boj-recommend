package com.daun.word.chapter.domain.repository;

import com.daun.word.chapter.domain.Chapter;
import com.daun.word.chapter.domain.vo.ChapterWordMapping;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository {

    /* 챕터 저장*/
    public int save(Chapter chapter);

    /* 챕터-단어 매핑 저장 */
    public int saveChapterWordMapping(ChapterWordMapping chapterWordMapping);
}
