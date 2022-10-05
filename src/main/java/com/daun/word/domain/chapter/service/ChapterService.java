package com.daun.word.domain.chapter.service;

import com.daun.word.domain.chapter.domain.Chapter;
import com.daun.word.domain.chapter.domain.repository.ChapterRepository;
import com.daun.word.domain.chapter.domain.vo.ChapterWordMapping;
import com.daun.word.domain.chapter.dto.ChapterSaveRequest;
import com.daun.word.domain.chapter.dto.ChapterSaveResponse;
import com.daun.word.global.Id;
import com.daun.word.domain.quiz.dto.QuizResponse;
import com.daun.word.domain.quiz.dto.QuizSaveRequest;
import com.daun.word.domain.quiz.service.QuizService;
import com.daun.word.domain.word.domain.Words;
import com.daun.word.domain.word.domain.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChapterService {

    private final QuizService quizService;

    private final ChapterRepository chapterRepository;

    private final WordRepository wordRepository;

    //TODO: 만약 이미 존재하는 단어라면 ?
    //TODO: chapter -> word -mapping 꼭 해야하는건지 고민해봐야 된다.
    @Transactional
    public ChapterSaveResponse save(ChapterSaveRequest request) {
        request.getWords().forEach(wordRepository::save);
        Words words = new Words(request.getWords());
        Chapter chapter = new Chapter(request.getWorkBookId(), request.getTitle(), words);
        chapterRepository.save(chapter);
        List<QuizResponse> quizs = new ArrayList<>();

        words.forEach(word -> {
            quizs.add(quizService.save(new QuizSaveRequest(Id.of(Chapter.class, chapter.getId()), word)));
            chapterRepository.saveChapterWordMapping(new ChapterWordMapping(chapter, word));
        });
        return new ChapterSaveResponse(chapter.getTitle(), words, quizs);
    }
}
