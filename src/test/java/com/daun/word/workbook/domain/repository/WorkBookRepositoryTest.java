package com.daun.word.workbook.domain.repository;

import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import com.daun.word.workbook.domain.WorkBook;
import com.daun.word.workbook.domain.vo.Author;
import com.daun.word.workbook.domain.vo.Description;
import com.daun.word.workbook.domain.vo.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class WorkBookRepositoryTest {

    @Autowired
    private WorkBookRepository workBookRepository;

    @DisplayName(value = "단어장을 저장한다")
    @Test
    void save() throws Exception {
        //given
        WorkBook workBook = new WorkBook(new Title("재밌는 단어장"),
                new Author("tester"),
                new Description("아주 친절한 설명"),
                "https://weword.com/image"
        );
        //when
        int saved = workBookRepository.save(workBook);
        //then
        assertThat(saved).isEqualTo(1);
    }

}
