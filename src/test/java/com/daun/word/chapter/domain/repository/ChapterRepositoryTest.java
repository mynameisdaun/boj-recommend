package com.daun.word.chapter.domain.repository;

import com.daun.word.chapter.domain.Chapter;
import com.daun.word.chapter.domain.vo.ChapterWordMapping;
import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.Words;
import com.daun.word.workbook.domain.vo.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class ChapterRepositoryTest {

    @Autowired
    private ChapterRepository chapterRepository;

    @DisplayName(value = "단어장을 저장한다")
    @Test
    void save_chapter() throws Exception {
        //given
        Words words = new Words(new ArrayList<>(Arrays.asList(word())));
        Chapter chapter = new Chapter(workbook().getId(), new Title("day 1"), words);
        //when
        int saved = chapterRepository.save(chapter);
        //then
        assertThat(saved).isEqualTo(1);
    }

    @DisplayName(value = "단어장_단어 매핑을 저장한다")
    @Test
    void saveChapterWordMapping() throws Exception {
        //given
        Chapter chapter = chapter();
        Word word = word();
        //when
        int saved = chapterRepository.saveChapterWordMapping(new ChapterWordMapping(chapter, word));
        //then
        assertThat(saved).isEqualTo(1);
    }

}
