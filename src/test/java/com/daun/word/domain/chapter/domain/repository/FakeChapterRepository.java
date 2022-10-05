package com.daun.word.domain.chapter.domain.repository;

import com.daun.word.domain.chapter.domain.Chapter;
import com.daun.word.domain.chapter.domain.vo.ChapterWordMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.daun.word.Fixture.Fixture.*;

public class FakeChapterRepository implements ChapterRepository {

    private final Map<Integer, Chapter> chapterTable;
    private final Map<Integer, ChapterWordMapping> chapterWordMappingTable;

    /* init */
    public FakeChapterRepository() {
        this.chapterTable = new HashMap<>();
        this.chapterWordMappingTable = new HashMap<>();
        chapterTable.put(chapter().getId(), chapter());
        chapterWordMappingTable.put(chapterWordMapping_1().getId(), chapterWordMapping_1());
        chapterWordMappingTable.put(chapterWordMapping_2().getId(), chapterWordMapping_2());
        chapterWordMappingTable.put(chapterWordMapping_3().getId(), chapterWordMapping_3());
    }

    @Override
    public int save(Chapter chapter) {
        chapter.setId(chapterTable.size());
        chapter.setCreatedAt(LocalDateTime.now());
        chapter.setUpdatedAt(LocalDateTime.now());
        chapterTable.put(chapterTable.size(), chapter);
        return 1;
    }

    @Override
    public int saveChapterWordMapping(ChapterWordMapping chapterWordMapping) {
        chapterWordMapping.setId(chapterWordMappingTable.size());
        chapterWordMapping.setCreatedAt(LocalDateTime.now());
        chapterWordMapping.setUpdatedAt(LocalDateTime.now());
        chapterWordMappingTable.put(chapterWordMappingTable.size(), chapterWordMapping);
        return 1;
    }
}
