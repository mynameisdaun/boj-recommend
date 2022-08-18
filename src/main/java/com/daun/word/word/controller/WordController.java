package com.daun.word.word.controller;

import com.daun.word.word.domain.Word;
import com.daun.word.word.dto.WordSaveRequest;
import com.daun.word.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/word")
public class WordController {

    private final WordService wordService;

    public ResponseEntity<?> save(@RequestBody WordSaveRequest wordSaveRequest) {
        return ResponseEntity.status(201).body(wordService.save(wordSaveRequest));
    }

}
