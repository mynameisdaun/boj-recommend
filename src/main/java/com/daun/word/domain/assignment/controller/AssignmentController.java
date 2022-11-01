package com.daun.word.domain.assignment.controller;

import com.daun.word.domain.assignment.dto.AssignRequest;
import com.daun.word.domain.assignment.dto.StudyAssignRequest;
import com.daun.word.domain.assignment.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("")
    public ResponseEntity<?> assign(@Valid @RequestBody AssignRequest request) {
        return ResponseEntity.status(201).body(assignmentService.assign(request));
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(200)
                .body(assignmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.status(200)
                .body(assignmentService.findById(id));
    }

    @PostMapping("/study")
    public ResponseEntity<?> studyAssign(@Valid @RequestBody StudyAssignRequest request) throws AuthenticationException {
        return ResponseEntity.status(201).body(assignmentService.assignForStudy(request));
    }
}
