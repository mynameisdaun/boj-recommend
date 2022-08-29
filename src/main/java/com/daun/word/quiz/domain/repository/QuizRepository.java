package com.daun.word.quiz.domain.repository;

import com.daun.word.commons.Id;
import com.daun.word.quiz.domain.Quiz;
import com.daun.word.word.domain.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface QuizRepository {

    /* 퀴즈를 저장한다 */
    int save(@Param("quiz") Quiz quiz);

    /* 퀴즈를 업데이트 한다 */
    int update(@Param("quiz") Quiz quiz);

    /* id로 퀴즈를 조회한다. */
    Optional<Quiz> findById(@Param("id") Id<Quiz, Integer> id);

    /* option 찾기, find result map의 서브 쿼리 */
    List<Word> findOptions(@Param("options") String options);

}
