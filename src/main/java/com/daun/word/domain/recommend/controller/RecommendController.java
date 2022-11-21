package com.daun.word.domain.recommend.controller;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.recommend.dto.RecommendRequest;
import com.daun.word.domain.recommend.dto.RecommendResponse;
import com.daun.word.domain.recommend.dto.StudyRecommendRequest;
import com.daun.word.domain.recommend.dto.StudyRecommendResponse;
import com.daun.word.domain.recommend.service.RecommendService;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import com.daun.word.domain.study.service.StudyHashService;
import com.daun.word.domain.study.service.StudyService;
import com.daun.word.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    private final StudyService studyService;

    private final StudyHashService studyHashService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> recommend(@Valid @RequestBody RecommendRequest request) {
        return ResponseEntity.status(201)
                .body(new ApiResponse(new RecommendResponse(recommendService.recommend(request))));
    }

    @PostMapping("/study")
    public ResponseEntity<ApiResponse> studyRecommend(@Valid @RequestBody StudyRecommendRequest request) throws AuthenticationException {
        //TODO: auth filter에서 한번에 진행하자
        Study study = studyService.findById(request.getStudy());
        study.auth(request.getHash(), studyHashService);
        RecommendRequest req = new RecommendRequest(study, request.getType(), request.getQuery());
        return ResponseEntity.status(201)
                .body(new ApiResponse(new StudyRecommendResponse(study, recommendService.recommend(req))));
    }
}
