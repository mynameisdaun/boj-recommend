package com.daun.word.chapter.service;

import com.daun.word.chapter.domain.Chapter;
import com.daun.word.chapter.domain.repository.ChapterRepository;
import com.daun.word.chapter.domain.vo.ChapterWordMapping;
import com.daun.word.chapter.dto.ChapterSaveRequest;
import com.daun.word.chapter.dto.ChapterSaveResponse;
import com.daun.word.word.domain.Words;
import com.daun.word.word.domain.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ChapterService {
    private final Logger logger = LoggerFactory.getLogger(ChapterService.class);
    private final ChapterRepository chapterRepository;

    private final WordRepository wordRepository;

    public ChapterSaveResponse save(ChapterSaveRequest request) {
        request.getWords().forEach(wordRepository::save);
        Words words = new Words(request.getWords());
        Chapter chapter = new Chapter(request.getWorkBookId(), request.getTitle(), words);
        chapterRepository.save(chapter);
        words.forEach(word -> {
            chapterRepository.saveChapterWordMapping(new ChapterWordMapping(chapter, word));
        });
        return new ChapterSaveResponse(chapter.getTitle(), words);
    }
}
