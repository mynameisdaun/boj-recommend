package com.daun.word.word.controller;

import com.daun.word.word.dto.WordSaveRequest;
import com.daun.word.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/word")
public class WordController {

    private final WordService wordService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody WordSaveRequest wordSaveRequest) {
        return ResponseEntity.status(201).body(wordService.save(wordSaveRequest));
    }

}
