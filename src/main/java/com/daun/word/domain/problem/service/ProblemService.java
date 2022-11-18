package com.daun.word.domain.problem.service;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.ProblemTag;
import com.daun.word.domain.problem.domain.Tag;
import com.daun.word.domain.problem.domain.repository.*;
import com.daun.word.domain.problem.dto.ManualUpdateRequest;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;
import com.daun.word.global.infra.solvedac.dto.TierCounts;
import com.daun.word.global.vo.Tier;
import com.daun.word.global.vo.Title;
import com.daun.word.global.vo.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemService {

    private final ProblemRepository problemRepository;

    private final TagRepository tagRepository;

    private final ProblemTagRepository problemTagRepository;

    private final SolvedAcClient solvedAcClient;

    /**
     * BOJ문제 로컬 저장
     *
     * @param request
     * @return Problem
     */
    @Transactional
    public Problem save(final SolvedAcProblem request) {
        checkArgument(request != null, "올바르지 않은 요청입니다");
        //TODO: 생성로직 problem 안으로 옮기자
        final Problem problem = problemRepository.save(new Problem(request));

        final List<ProblemTag> problemTags = new ArrayList<>();
        for (final SolvedAcProblem.Tag t : request.getTags()) {
            final Tag tag = tagRepository.saveAndFlush(new Tag(t));
            problemTags.add(new ProblemTag(problem, tag));
        }
        //TODO: cascadetype.All 로 변경해서 한번에 영속화하자..

        problemTags.forEach(pt -> problemTagRepository.save(pt));
        problem.addTags(problemTags);
        return problem;
    }

    /**
     * 문제 수동 저장
     * SolvedAc Api를 통해 문제를 검색하고 로컬 서버에 저장한다
     *
     * @param id
     * @return Problem
     * @throws NoSuchElementException solvedAc Api로 해당 번호의 문제가 검색되지 않을때 "{id} 번호의 문제를 찾을 수 없습니다"
     */
    @Transactional
    public Problem manual(Integer id) {
        SolvedAcProblem problem = solvedAcClient.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id + " 번호의 문제를 찾을 수 없습니다"));
        return save(problem);
    }

    /**
     * 문제를 조회한다
     * 로컬에서 문제를 찾지 못한 경우,
     * 1. BOJ Api에서 문제를 검색해오고
     * 2. 로컬에 업데이트 한 후
     * 3. 문제를 반환한다
     *
     * @param id Long
     * @return Problem
     */
    @Transactional
    public Problem findById(final Integer id) {
        return problemRepository.findById(id)
                .orElseGet(() -> manual(id));
    }

    /**
     * id 리스트로 문제를 조회한다
     * 요청한 아이디 갯수 만큼 로컬에서 문제를 찾지 못한다면,
     * BOJ에서 문제를 검색해온다
     *
     * @param ids
     * @return List
     */
    @Transactional
    public List<Problem> findByIdIn(final List<Integer> ids) {
        List<Problem> find = problemRepository.findAllByIdIn(ids);
        if (find.size() != ids.size()) {
            Set<Integer> findIds = find.stream().map(Problem::getId).collect(toSet());
            find.addAll(
                    ids.stream()
                            .filter(id -> !findIds.contains(id))
                            .map(this::manual)
                            .collect(toList()));
        }
        return find;
    }

    /**
     * 최소, 최대 사이에 있는 문제 조회
     *
     * @param min 최소 티어
     * @param max 최대 티어
     * @return List
     */
    @Transactional(readOnly = true)
    public List<Problem> findAllByTierBetween(final Tier min, final Tier max) {
        return problemRepository.findAllByTierBetweenOrderByAcceptedUserCountDesc(min, max);
    }

    /**
     * SolvedAc로 부터 문제 업데이트
     * 1. SolvedAc에서 티어별 문제 수를 조회한다
     * 2. SolvedAc의 문제 수가 로컬에 있는 문제 수 보다 많을 경우 업데이트를 진행한다
     * 2-1. solvedAc 에서 푼 사람 수를 기준으로 오름차순 정렬하여 조회한다
     * 2-2  local에 존재 하지 않는 문제를 업데이트한다
     *
     * @param request
     */
    @Transactional
    public int manual(final ManualUpdateRequest request) {
        final TierCounts counts = solvedAcClient.problemCountGroupByTier();
        log.error("counts: {}", counts);
        for (int i = request.min(); i <= request.max(); i++) {
            final Tier tier = new Tier(i);
            final int solvedAc = counts.get(i).getCount();
            final int local = problemRepository.countByTier(tier);
            int page = 1;
            if (solvedAc > local) {
                List<Problem> localIds = problemRepository.findAllByTierBetweenOrderByAcceptedUserCountDesc(tier, tier);
                final int gap = solvedAc - local;
                int saveCount = 0;
                log.error("gap: {}", gap);
                while (gap > saveCount) {
                    StringBuilder query = new StringBuilder("*")
                            .append(tier.getRate());
                    List<Integer> solvedAcIds = solvedAcClient.search(query.toString(), page, "solved", "asc")
                            .toIdList();

                    List<Integer> filtered = solvedAcIds.stream()
                            .filter(id -> !localIds.contains(id))
                            .collect(toList());

                    List<SolvedAcProblem> unsaved = solvedAcClient.findByIdsIn(filtered);
                    for (SolvedAcProblem p : unsaved) {
                        Problem saved = problemRepository.save(new Problem(p));
                        saveCount++;
                        log.info("saveCount:{}, \n {}",saveCount, saved);
                    }
                    page++;
                }
            }
        }
        return 1;
    }
}
