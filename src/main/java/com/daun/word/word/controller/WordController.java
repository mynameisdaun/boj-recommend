package com.daun.word.word.controller;

import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.dto.WordSaveRequest;
import com.daun.word.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/word")
public class WordController {
    private final Logger logger = LoggerFactory.getLogger(WordController.class);
    private final WordService wordService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody WordSaveRequest wordSaveRequest) {
        return ResponseEntity.status(201).body(wordService.save(wordSaveRequest));
    }

    @GetMapping("/{english}")
    public ResponseEntity<?> findByEnglish(@PathVariable English english) {
        Word word = wordService.findByEnglish(english);
        return ResponseEntity.status(200).body(word);
    }

}
