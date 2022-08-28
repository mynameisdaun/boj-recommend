package com.daun.word.assignment.controller;

import com.daun.word.assignment.dto.AssignmentRequest;
import com.daun.word.assignment.dto.AssignmentSaveRequest;
import com.daun.word.assignment.service.AssignmentService;
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
    public ResponseEntity<?> save(@Valid @RequestBody AssignmentSaveRequest request) {
        return ResponseEntity.status(201).body(assignmentService.save(request));
    }

    @GetMapping("/{assignmentId}")
    public ResponseEntity<?> find(@PathVariable AssignmentRequest request) {
        return ResponseEntity.status(200).body(assignmentService.findById(request));
    }

    @PutMapping("/{assignmentId}/open/{detailId}")
    public ResponseEntity<?> open(
            @PathVariable("assignmentId") Integer assignmentId, @PathVariable("detailId") Integer detailId) {
        return ResponseEntity.status(200).body(assignmentService.open(detailId));
    }

}
