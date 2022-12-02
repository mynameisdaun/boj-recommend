package com.daun.word.domain.problem.controller;

import com.daun.word.domain.problem.dto.ManualUpdateRequest;
import com.daun.word.domain.problem.dto.ProblemDTO;
import com.daun.word.domain.problem.service.ProblemService;
import com.daun.word.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.daun.word.global.constant.ApiResponseCode.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/problem"))
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse(OK, new ProblemDTO(problemService.findById(id))));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> save(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse(OK, new ProblemDTO(problemService.manual(id))));
    }

    @PostMapping("/manual")
    public ResponseEntity<ApiResponse> manual(@RequestBody ManualUpdateRequest request) {
        return ResponseEntity.status(200).body(new ApiResponse(OK, problemService.manual(request)));
    }
}
