package com.daun.word.domain.assignment.controller;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.dto.AssignmentSaveRequest;
import com.daun.word.domain.assignment.service.AssignmentService;
import com.daun.word.global.GlobalId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody AssignmentSaveRequest request) {
        return ResponseEntity.status(201).body(assignmentService.save(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.status(200)
                .body(assignmentService.findById(id));
    }
}
