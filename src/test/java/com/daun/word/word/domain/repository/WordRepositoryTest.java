package com.daun.word.word.domain.repository;

import com.daun.word.global.Id;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WordRepositoryTest {

    @Autowired
    private WordRepository wordRepository;

    @DisplayName(value = "단어를 저장한다")
    @Test
    void save() throws Exception {
        //given
        Word word = new Word(new English("newWord"), new Korean("새로운단어"), new Email("tester1@weword.com"));
        //when
        int saved = wordRepository.save(word);
        //then
        assertThat(saved).isEqualTo(1);
    }

    @DisplayName(value = "영어 표기로 단어를 조회한다")
    @Test
    void findByEnglish() throws Exception {
        //given
        English english = new English("word");
        //when
        Word word = wordRepository.findByEnglish(english)
                .orElseThrow(NoSuchElementException::new);
        //then
        assertThat(word).isNotNull();
        assertAll(
                () -> assertThat(word.getEnglish().getValue()).isEqualTo("word"),
                () -> assertThat(word.getKorean().getValue()).isEqualTo("단어")
        );
    }

    @DisplayName(value = "저장되지 않는 영어단어를 조회할 수는 없다")
    @Test
    void findByEnglish_no_exist() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> wordRepository.findByEnglish(new English("noexist"))
                .orElseThrow(NoSuchElementException::new))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName(value = "영어 표기들로 단어들을 조회한다")
    @Test
    void findByEnglishIn() throws Exception {
        //given
        List<English> finds = new ArrayList<English>();
        finds.add(new English("we"));
        finds.add(new English("word"));
        //when
        List<Word> words = wordRepository.findByEnglishIn(finds);
        //then
        assertThat(words).isNotNull();
        assertAll(
                () -> assertThat(words.size()).isEqualTo(2)
        );
    }

    @DisplayName(value = "단어 아이디들로 단어들을 조회한다")
    @Test
    void findByIdIn() throws Exception {
        //given
        List<Id<Word, Integer>> finds = new ArrayList<Id<Word, Integer>>();
        finds.add(Id.of(Word.class, 1));
        finds.add(Id.of(Word.class, 2));
        finds.add(Id.of(Word.class, 3));
        //when
        List<Word> words = wordRepository.findByIdIn(finds);
        //then
        assertThat(words).isNotNull();
        assertAll(
                () -> assertThat(words.size()).isEqualTo(3)
        );
    }

    @DisplayName(value = "저장되지 않는 영어단어를 조회할 수는 없다")
    @Test
    void findByEnglishIn_no_exist() throws Exception {
        //given
        List<English> finds = new ArrayList<English>();
        finds.add(new English("neverexist"));
        //when
        List<Word> words = wordRepository.findByEnglishIn(finds);
        //then
        assertThat(words).isNotNull();
        assertAll(
                () -> assertThat(words.isEmpty()).isTrue()
        );
    }

    @DisplayName(value = "단어 총 갯수를 조회한다")
    @Test
    void count() throws Exception {
        //given&&when
        int count = wordRepository.count();
        //then
        assertThat(count).isEqualTo(12);
    }
}
