package com.daun.word.word.service;

import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.repository.WordRepository;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.dto.WordSaveRequest;
import com.daun.word.word.dto.WordSaveResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class WordService {
    private final Logger logger = LoggerFactory.getLogger(WordService.class);

    private final WordRepository wordRepository;

    /* 단어 저장하기 */
    public WordSaveResponse save(WordSaveRequest wordSaveRequest) {
        //TODO: 이미 저장되어있는 단어 처리에 대한 응답 정의, 현재 처리는 너무 이상하다고 생각된다.
        if (wordRepository.findByEnglish(wordSaveRequest.getEnglish()).isPresent()) {
            throw new IllegalStateException("이미 저장되어 있는 단어입니다.");
        }
        Word word = new Word(wordSaveRequest.getEnglish(), wordSaveRequest.getKorean(), wordSaveRequest.getCreatedBy());
        wordRepository.save(word);
        return WordSaveResponse.fromWord(word);
    }

    /* 영어 표기로 단어 조회하기 */
    public Word findByEnglish(English english) {
        return wordRepository.findByEnglish(english)
                .orElseThrow(NoSuchElementException::new);
    }
}
