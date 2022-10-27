package com.daun.word.domain.assignment.controller;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.dto.AssignmentRequest;
import com.daun.word.domain.assignment.service.AssignmentService;
import com.daun.word.global.GlobalId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody AssignmentRequest request) {
        return ResponseEntity.status(201).body(assignmentService.save(request));
    }

    @GetMapping("/{assignmentId}")
    public ResponseEntity<?> findById(@PathVariable Integer assignmentId) {
        return ResponseEntity.status(200)
                .body(assignmentService.findById(GlobalId.of(Assignment.class, assignmentId)));
    }
}
