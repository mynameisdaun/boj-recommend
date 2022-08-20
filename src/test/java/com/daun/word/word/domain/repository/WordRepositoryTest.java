package com.daun.word.word.domain.repository;

import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@MybatisTest
class WordRepositoryTest {

    @Autowired
    private WordRepository wordRepository;

    @DisplayName(value = "영어 표기로 단어를 조회한다")
    @Test
    void findByEnglish() throws Exception {
        //given
        English english = new English("word");
        //when
        Word word = wordRepository.findByEnglish(english);
        //then
        assertThat(word).isNotNull();
        assertAll(
                () -> assertThat(word.getEnglish().getValue()).isEqualTo("word"),
                () -> assertThat(word.getKorean().getValue()).isEqualTo("단어")
        );
    }
}
