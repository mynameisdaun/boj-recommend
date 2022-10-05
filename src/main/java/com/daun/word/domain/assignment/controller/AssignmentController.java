package com.daun.word.domain.assignment.controller;

import com.daun.word.domain.assignment.domain.PAssignment;
import com.daun.word.domain.assignment.dto.AssignmentRequest;
import com.daun.word.domain.assignment.dto.AssignmentSaveRequest;
import com.daun.word.domain.assignment.dto.d_AssignmentSaveRequest;
import com.daun.word.domain.assignment.service.AssignmentService;
import com.daun.word.global.Id;
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
        return ResponseEntity.status(200)
                .body(assignmentService.findById(Id.of(PAssignment.class, request.getAssignmentId())));
    }

    @PostMapping("/d")
    public ResponseEntity<?> save_d(@Valid @RequestBody d_AssignmentSaveRequest request) {
        return ResponseEntity.status(201).body(assignmentService.save_d(request));
    }

    @GetMapping("/d/{assignmentId}")
    public ResponseEntity<?> find_d(@PathVariable AssignmentRequest request) {
        return ResponseEntity.status(200).body(assignmentService.findById_d(request));
    }

    @PutMapping("/d/{assignmentId}/open/{detailId}")
    public ResponseEntity<?> open(
            @PathVariable("assignmentId") Integer assignmentId, @PathVariable("detailId") Integer detailId) {
        return ResponseEntity.status(200).body(assignmentService.open_d(detailId));
    }

}
