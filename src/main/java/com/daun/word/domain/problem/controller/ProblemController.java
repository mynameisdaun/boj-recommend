package com.daun.word.domain.problem.controller;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.service.ProblemService;
import com.daun.word.global.Id;
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
        return ResponseEntity.status(200).body(problemService.findById(Id.of(Problem.class, id)));
    }

    @PostMapping("/manual-update")
    public ResponseEntity<?> manualUpdate() {
        return ResponseEntity.status(200).body(problemService.manualUpdate());
    }
}
