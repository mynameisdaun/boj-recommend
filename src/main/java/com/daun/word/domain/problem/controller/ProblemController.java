package com.daun.word.domain.problem.controller;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.service.ProblemService;
import com.daun.word.global.GlobalId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/problem"))
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(problemService.findById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> save(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(problemService.manual(id));
    }
}
