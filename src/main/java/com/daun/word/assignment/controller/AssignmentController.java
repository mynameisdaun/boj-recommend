package com.daun.word.assignment.controller;

import com.daun.word.assignment.dto.AssignmentSaveRequest;
import com.daun.word.assignment.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody AssignmentSaveRequest request) {
        return ResponseEntity.status(201).body(assignmentService.save(request));
    }



}
