package com.daun.word.domain.workbook.controller;

import com.daun.word.domain.workbook.dto.WorkBookExcelSaveRequest;
import com.daun.word.domain.workbook.dto.WorkBookSaveRequest;
import com.daun.word.domain.workbook.service.WorkBookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workbook")
public class WorkBookController {
    private final Logger logger = LoggerFactory.getLogger(WorkBookController.class);
    private final WorkBookService workBookService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody WorkBookSaveRequest workBookSaveRequest) {
        return ResponseEntity.status(201).body(workBookService.save(workBookSaveRequest));
    }

    @PostMapping(value = "/excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> excel(@ModelAttribute WorkBookExcelSaveRequest request) throws IOException {
        return ResponseEntity.status(200).body(workBookService.saveFromExcel(request));
    }

}
