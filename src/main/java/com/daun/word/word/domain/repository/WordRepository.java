package com.daun.word.word.domain.repository;

import com.daun.word.global.Id;
import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface WordRepository {

    /* 단어 저장 */
    int save(Word word);

    /* 영어 표기로 단어 조회*/
    Optional<Word> findByEnglish(@Param("english") English english);

    /* 영어 표기 리스트로 단어 리스트 조회 */
    List<Word> findByEnglishIn(@Param("englishes") List<English> englishes);

    /* 단어 아이디 리스트로 단어 리스트 조회 */
    List<Word> findByIdIn(@Param("ids") List<Id<Word, Integer>> ids);

    /* 단어 총 갯수 카운트 */
    int count();
}
