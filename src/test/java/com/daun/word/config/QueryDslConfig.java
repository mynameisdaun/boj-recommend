package com.daun.word.config;

import com.daun.word.domain.problem.domain.repository.ProblemQueryRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class QueryDslConfig {

    @Bean
    public ProblemQueryRepositoryImpl problemQueryRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        return new ProblemQueryRepositoryImpl(jpaQueryFactory);
    }
}
