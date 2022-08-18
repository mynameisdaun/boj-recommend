package com.daun.word.word.service;

import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.repository.WordRepository;
import com.daun.word.word.dto.WordSaveRequest;
import com.daun.word.word.dto.WordSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public WordSaveResponse save(WordSaveRequest wordSaveRequest) {
        //TODO: 이미 저장되어있는 단어 처리에 대한 응답 정의
        if(wordRepository.findByEnglish(wordSaveRequest.getEnglish()) != null) {
            throw new IllegalStateException("이미 저장되어 있는 단어입니다.");
        }
        Word word = new Word(wordSaveRequest.getEnglish(), wordSaveRequest.getKorean());
        wordRepository.save(word);
        return WordSaveResponse.fromWord(word);
    }
}