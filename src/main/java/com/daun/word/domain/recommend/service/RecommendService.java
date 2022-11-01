package com.daun.word.domain.recommend.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.recommend.domain.repository.RecommendRepository;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import com.daun.word.global.GlobalId;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.vo.Tier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendService {

    private final RecommendRepository recommendRepository;

    @Transactional
    public Recommend findById(UUID id) {
        return recommendRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());
    }


}
