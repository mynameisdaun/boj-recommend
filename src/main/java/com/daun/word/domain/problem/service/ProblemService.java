package com.daun.word.domain.problem.service;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.ProblemTag;
import com.daun.word.domain.problem.domain.repository.JpaProblemRepository;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.problem.domain.repository.JpaProblemTagRepository;
import com.daun.word.domain.problem.domain.repository.JpaTagRepository;
import com.daun.word.global.GlobalId;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.infra.solvedac.dto.ProblemCount;
import com.daun.word.global.vo.Tier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemService {

    private final ProblemRepository oldRepository;

    private final JpaProblemTagRepository problemTagRepository;

    private final JpaTagRepository tagRepository;

    private final JpaProblemRepository problemRepository;

    private final SolvedAcClient solvedAcClient;


    @Transactional
    public Problem findById(Integer id) {
        return problemRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Problem save(Problem problem) {
        Problem p = problemRepository.save(problem);
        List<ProblemTag> problemTags = new ArrayList<>();
        throw new NotImplementedException();
    }

    @Transactional
    public List<Problem> manualUpdate() {
        //백준에 등록되어 있는 문제 수와, 로컬에 있는 문제 수를 비교한 후에 차이가 나는 만큼 업데이트를 한다.
        //업데이트를 할때는 많이 풀지 않은 수로 조회를 한다(왜냐면 가장 최신에 나왓을 테니까 가장 적게 풀었겠지..)
        try {
            final List<ProblemCount> solvedAcPcs = solvedAcClient.problemCountGroupByLevel();
            final List<ProblemCount> localPcs = oldRepository.countByGroup();
            final List<Problem> savedProblems = new ArrayList<>();
            log.info("\n #################### Manual Update Start #################### \n");
            for (int i = 1; i <= 30; i++) {
                log.info("\n ############### Tier {} 확인해봅니다 ############### \n", i);
                final Tier tier = new Tier(i);
                final ProblemCount solvedAc = solvedAcPcs.stream()
                        .filter(s -> s.getLevel() == tier.getLevel())
                        .findFirst()
                        .orElseThrow(IllegalStateException::new);
                final ProblemCount local = localPcs.stream()
                        .filter(l -> l.getLevel() == tier.getLevel())
                        .findFirst()
                        .orElseGet(() -> new ProblemCount(tier.getLevel(), 0));
                int diff = solvedAc.getCount() - local.getCount();
                log.info("\n ############### {} 개의 새로운 문제가 있습니다. ############### \n", diff);
                int page = 1;
                // ex) *b5..b5
                StringBuilder query = new StringBuilder("*").append(tier.getRate()).append("..").append(tier.getRate());
                while (diff > 0) {
                    log.info("\n ############### {} 번째 검색 ############### \n", page);
                    List<GlobalId<Problem, Integer>> solvedAcIds = solvedAcClient.search(query.toString(), page, "solved", "asc")
                            .toProblemIds();
                    List<GlobalId<Problem, Integer>> localIds = oldRepository.findByIdIn(solvedAcIds)
                            .stream()
                            .map(p -> GlobalId.of(Problem.class, p.getId()))
                            .collect(toList());
                    List<Problem> unsavedProblems = solvedAcClient.findByIdsIn(solvedAcIds.stream()
                            .filter(id -> !localIds.contains(id))
                            .collect(toList()));
                    log.info("\n ############### 로컬에 저장되지 않은 {} 개의 문제를 찾았습니다. ############### \n", unsavedProblems.size());
                    for (Problem p : unsavedProblems) {
                        try {
                            savedProblems.add(save(p));
                        } catch (Exception e) {
                            log.error("문제 저장 과젱에서 발생했습니다, {}", p);
                            e.printStackTrace();
                        }
                        diff--;
                    }
                    page++;
                }
            }
            return savedProblems;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<ProblemCount> findInconsistencies(List<ProblemCount> solvedAcPcs, List<ProblemCount> localPcs) {
        List<ProblemCount> inconsistencies = new ArrayList<>();
        for (ProblemCount solvedAc : solvedAcPcs) {
            ProblemCount local = localPcs.stream().
                    filter(pc -> pc.getLevel() == solvedAc.getLevel())
                    .findFirst()
                    .orElseGet(() -> new ProblemCount(solvedAc.getLevel(), 0));
            if (!solvedAc.equals(local)) {
                inconsistencies.add(local);
            }
        }
        return inconsistencies;
    }
}
