package com.daun.word.quiz.domain.repository;

import com.daun.word.global.Id;
import com.daun.word.quiz.domain.Quiz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}
