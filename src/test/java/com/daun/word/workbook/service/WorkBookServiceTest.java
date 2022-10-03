package com.daun.word.workbook.service;

import com.daun.word.chapter.dto.ChapterSaveRequest;
import com.daun.word.chapter.dto.ChapterSaveResponse;
import com.daun.word.chapter.service.ChapterService;
import com.daun.word.infra.excel.ExcelClient;
import com.daun.word.infra.image.ImageClient;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.quiz.dto.QuizResponse;
import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.Words;
import com.daun.word.workbook.domain.repository.FakeWorkBookRepository;
import com.daun.word.workbook.domain.vo.Author;
import com.daun.word.workbook.domain.vo.Description;
import com.daun.word.global.vo.Title;
import com.daun.word.workbook.dto.WorkBookSaveRequest;
import com.daun.word.workbook.dto.WorkBookSaveResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WorkBookServiceTest {

    @InjectMocks
    private WorkBookService workBookService;

    @Mock
    private ChapterService chapterService;

    @Mock
    private ExcelClient excelClient;

    @Mock
    private ImageClient imageClient;

    @BeforeEach
    public void setUp() {
        workBookService = new WorkBookService(
                new FakeWorkBookRepository(),
                chapterService,
                excelClient,
                imageClient
        );
    }

    @DisplayName(value = "save")
    @Test
    void save() throws Exception {
        //given
        List<Word> words = new ArrayList<Word>(Arrays.asList(word_1()));
        List<ChapterSaveRequest> chapterRequests = Arrays.asList(new ChapterSaveRequest(new Title("Day 1"), words()));
        chapterRequests.forEach(req -> {
            given(chapterService.save(req))
                    .willReturn(new ChapterSaveResponse(req.getTitle(), new Words(req.getWords()), new ArrayList<>(Arrays.asList(new QuizResponse(quiz_un_submitted())))));
        });
        WorkBookSaveRequest request = new WorkBookSaveRequest(
                new Title("새로운 단어장"),
                new Author("테스터"),
                new Description("아주 아주 친절한 설명"),
                new Email("tester1@weword.com"),
                new ArrayList<ChapterSaveRequest>(chapterRequests),
                "fake-save-url"
        );
        //when
        WorkBookSaveResponse response = workBookService.save(request);
        //then
        assertThat(response).isNotNull();
        assertAll(
                () -> assertThat(response.getWorkBook().getTitle()).isEqualTo(new Title("새로운 단어장")),
                () -> assertThat(response.getWorkBook().getAuthor()).isEqualTo(new Author("테스터")),
                () -> assertThat(response.getWorkBook().getDescription()).isEqualTo(new Description("아주 아주 친절한 설명")),
                () -> assertThat(response.getChapters().size()).isEqualTo(1),
                () -> assertThat(response.getChapters().get(0).getWords().size()).isEqualTo(3),
                () -> assertThat(response.getChapters().get(0).getQuizs().size()).isEqualTo(1)
        );
    }
}
