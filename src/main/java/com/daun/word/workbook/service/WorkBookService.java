package com.daun.word.workbook.service;

import com.daun.word.chapter.dto.ChapterSaveRequest;
import com.daun.word.chapter.service.ChapterService;
import com.daun.word.workbook.domain.WorkBook;
import com.daun.word.workbook.domain.repository.WorkBookRepository;
import com.daun.word.workbook.dto.WorkBookSaveRequest;
import com.daun.word.workbook.dto.WorkBookSaveResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class WorkBookService {
    private final Logger logger = LoggerFactory.getLogger(WorkBookService.class);

    private final WorkBookRepository workBookRepository;

    private final ChapterService chapterService;

    public WorkBookSaveResponse save(WorkBookSaveRequest workBookSaveRequest) {
        WorkBook workBook = workBookSaveRequest.toWorkBook();
        logger.info(workBook.toString());
        workBookRepository.save(workBook);
        logger.info(workBook.toString());
        return new WorkBookSaveResponse(
                workBook,
                workBookSaveRequest.getChapters()
                        .stream()
                        .map(req -> chapterService.save(new ChapterSaveRequest(workBook.getId(), req.getTitle(), req.getWords())))
                        .collect(Collectors.toList()));
    }
}
