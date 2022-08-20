package com.daun.word.workbook.controller;

import com.daun.word.workbook.dto.WorkBookSaveRequest;
import com.daun.word.workbook.service.WorkBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workbook")
public class WorkBookController {
    private final WorkBookService workBookService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody WorkBookSaveRequest workBookSaveRequest) {
        return ResponseEntity.status(201).body(workBookService.save(workBookSaveRequest));
    }

}
