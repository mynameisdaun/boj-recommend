package com.daun.word.domain.problem.controller;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.service.ProblemService;
import com.daun.word.domain.word.domain.Word;
import com.daun.word.domain.word.domain.vo.English;
import com.daun.word.global.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/problem"))
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(problemService.findById(Id.of(Problem.class, id)));
    }
}
