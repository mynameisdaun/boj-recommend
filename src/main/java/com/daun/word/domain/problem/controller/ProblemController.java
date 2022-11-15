package com.daun.word.domain.problem.controller;

import com.daun.word.domain.problem.service.ProblemService;
import com.daun.word.global.dto.ApiResponse;
import com.daun.word.global.vo.Tier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/problem"))
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse(problemService.findById(id)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> save(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse(problemService.manual(id)));
    }

    @GetMapping("/between")
    public ResponseEntity<ApiResponse> save(@RequestParam Tier min, @RequestParam Tier max) {
        return ResponseEntity.status(200).body(new ApiResponse(problemService.findAllByTierBetween(min, max)));
    }
}
