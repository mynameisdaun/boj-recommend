package com.daun.word.word.domain.repository;

import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WordRepository {

    /* 단어 저장 */
    int save(Word word);

    /* 영어 표기로 단어 조회*/
    Optional<Word> findByEnglish(@Param("english") English english);

    /* 영어 표기 리스트로 단어 리스트 조회 */
    List<Word> findByEnglishIn(@Param("englishes") List<English> englishes);
}
