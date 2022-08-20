package com.daun.word.word.domain.repository;


import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface WordRepository {

    /* 단어 저장 */
    int save(Word word);

    /* 영어 표기로 단어 조회*/
    Word findByEnglish(@Param("english") English english);

    /* 한국어 표기로 단어 조회*/
    Word findByKorean(Korean korean);

    /* 영어 표기 리스트로 단어 리스트 조회 */
    List<Word> findByEnglishIn(List<English> englishes);
}
