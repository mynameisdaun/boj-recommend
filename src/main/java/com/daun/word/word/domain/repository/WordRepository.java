package com.daun.word.word.domain.repository;


import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository {

    /* 단어 저장 */
    int save(Word word);

    /* 영어 표기로 단어 조회*/
    Word findByEnglish(English english);

    /* 한국어 표기로 단어 조회*/
    Word findByKorean(Korean korean);
}
