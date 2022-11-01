package com.daun.word.domain.problem.service;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.ProblemTag;
import com.daun.word.domain.problem.domain.Tag;
import com.daun.word.domain.problem.domain.repository.*;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;
import com.daun.word.global.vo.Tier;
import com.daun.word.global.vo.Title;
import com.daun.word.global.vo.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemService {

    private final ProblemRepository problemRepository;

    private final TagRepository tagRepository;

    private final ProblemTagRepository problemTagRepository;

    private final SolvedAcClient solvedAcClient;

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
     * BOJ문제 로컬 저장
     *
     * @param request
     * @return Problem
     */
    @Transactional
    public Problem save(final SolvedAcProblem request) {
        checkArgument(request != null, "올바르지 않은 요청입니다");
        final Title title = new Title(request.getTitleKo());
        final URL url = new URL(request.getProblemId());
        final Tier tier = new Tier(request.getLevel());
        final Problem problem = problemRepository.save(new Problem(request.getProblemId(), title, url, tier, request.getAcceptedUserCount()));

        final List<ProblemTag> problemTags = new ArrayList<>();
        for (final SolvedAcProblem.Tag t : request.getTags()) {
            final Tag tag = tagRepository.saveAndFlush(new Tag(t));
            problemTags.add(new ProblemTag(problem, tag));
        }

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

//    @Transactional
//    public List<Problem> manualUpdate() {
//        //백준에 등록되어 있는 문제 수와, 로컬에 있는 문제 수를 비교한 후에 차이가 나는 만큼 업데이트를 한다.
//        //업데이트를 할때는 많이 풀지 않은 수로 조회를 한다(왜냐면 가장 최신에 나왓을 테니까 가장 적게 풀었겠지..)
//        try {
//            final List<ProblemCount> solvedAcPcs = solvedAcClient.problemCountGroupByLevel();
//            final List<ProblemCount> localPcs = ProblemRepository.countByGroup();
//            final List<Problem> savedProblems = new ArrayList<>();
//            log.info("\n #################### Manual Update Start #################### \n");
//            for (int i = 1; i <= 30; i++) {
//                log.info("\n ############### Tier {} 확인해봅니다 ############### \n", i);
//                final Tier tier = new Tier(i);
//                final ProblemCount solvedAc = solvedAcPcs.stream()
//                        .filter(s -> s.getLevel() == tier.getLevel())
//                        .findFirst()
//                        .orElseThrow(IllegalStateException::new);
//                final ProblemCount local = localPcs.stream()
//                        .filter(l -> l.getLevel() == tier.getLevel())
//                        .findFirst()
//                        .orElseGet(() -> new ProblemCount(tier.getLevel(), 0));
//                int diff = solvedAc.getCount() - local.getCount();
//                log.info("\n ############### {} 개의 새로운 문제가 있습니다. ############### \n", diff);
//                int page = 1;
//                // ex) *b5..b5
//                StringBuilder query = new StringBuilder("*").append(tier.getRate()).append("..").append(tier.getRate());
//                while (diff > 0) {
//                    log.info("\n ############### {} 번째 검색 ############### \n", page);
//                    List<GlobalId<Problem, Integer>> solvedAcIds = solvedAcClient.search(query.toString(), page, "solved", "asc")
//                            .toProblemIds();
//                    List<GlobalId<Problem, Integer>> localIds = ProblemRepository.findByIdIn(solvedAcIds)
//                            .stream()
//                            .map(p -> GlobalId.of(Problem.class, p.getId()))
//                            .collect(toList());
//                    List<Problem> unsavedProblems = solvedAcClient.findByIdsIn(solvedAcIds.stream()
//                            .filter(id -> !localIds.contains(id))
//                            .collect(toList()));
//                    log.info("\n ############### 로컬에 저장되지 않은 {} 개의 문제를 찾았습니다. ############### \n", unsavedProblems.size());
//                    for (Problem p : unsavedProblems) {
//                        try {
//                            savedProblems.add(save(p));
//                        } catch (Exception e) {
//                            log.error("문제 저장 과젱에서 발생했습니다, {}", p);
//                            e.printStackTrace();
//                        }
//                        diff--;
//                    }
//                    page++;
//                }
//            }
//            return savedProblems;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
