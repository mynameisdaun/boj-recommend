package com.daun.word.member;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDAO {
    List<TestDTO> getTestData();
}
