package com.daun.word.chapter.service;

import com.daun.word.chapter.domain.Chapter;
import com.daun.word.chapter.domain.repository.ChapterRepository;
import com.daun.word.chapter.domain.vo.ChapterWordMapping;
import com.daun.word.chapter.dto.ChapterSaveRequest;
import com.daun.word.chapter.dto.ChapterSaveResponse;
import com.daun.word.word.domain.Words;
import com.daun.word.word.domain.repository.WordRepository;
import com.daun.word.word.domain.vo.English;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;

    private final WordRepository wordRepository;

    public ChapterSaveResponse save(ChapterSaveRequest request) {
        Words words = new Words(wordRepository.findByEnglishIn(
                        request.getWords()
                                .stream()
                                .map(English::new)
                                .collect(Collectors.toList())));
        Chapter chapter = new Chapter(request.getWorkBookId(), request.getTitle(), words);
        chapterRepository.save(chapter);
        words.forEach(word -> {
            chapterRepository.saveChapterWordMapping(new ChapterWordMapping(chapter, word));
        });
        return new ChapterSaveResponse(chapter.getTitle(), words);
    }
}
