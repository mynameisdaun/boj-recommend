package com.daun.word.domain.reference.service;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.repository.ProblemRepository;
import com.daun.word.domain.reference.domain.Reference;
import com.daun.word.domain.reference.domain.repository.ReferenceRepository;
import com.daun.word.domain.reference.dto.RefRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReferenceService {

    private final ReferenceRepository referenceRepository;

    private final ProblemRepository problemRepository;

    private final EntityManager em;

    /**
     * 레퍼런스 추가용 api
     *
     * @param request
     * @return
     */
    public List<Reference> register(final RefRegisterRequest request) {
        List<Reference> references = new ArrayList<>();
        List<Problem> problems = problemRepository.findAllByIdIn(request.getProblemIds());
        for (Problem p : problems) {
            try {
                Reference saved = referenceRepository.save(new Reference(UUID.randomUUID(), p, request.getUrl(), false));
                references.add(saved);
                em.flush();
            } catch (Exception e) {
                log.error("{}", p.getId());
            }
        }
        return references;
    }
}
