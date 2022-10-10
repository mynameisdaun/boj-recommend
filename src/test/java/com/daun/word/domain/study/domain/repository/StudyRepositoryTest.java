package com.daun.word.domain.study.domain.repository;

import com.daun.word.domain.study.domain.Study;
import com.daun.word.global.utils.HashUtils;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.YesNo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Arrays;

import static com.daun.word.Fixture.Fixture.another_member;
import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudyRepositoryTest {

    @Autowired
    private StudyRepository studyRepository;

    @DisplayName(value = "스터디 그룹을 생성한다")
    @Test
    void save() throws Exception {
        //given
        Study study = new Study(another_member(), new Name("테스트 스터디"), HashUtils.sha256("key"), YesNo.N, Arrays.asList(another_member()));
        //when
        int saved = studyRepository.save(study);
        //then
        assertThat(study.getId()).isPositive();
        assertThat(study.getCreatedAt()).isNotNull();
        assertThat(study.getUpdatedAt()).isNotNull();
    }


}
