package com.daun.word.domain.recommend.service;

import com.daun.word.Fixture.Fixture;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.assignment.domain.repository.FakeAssignmentRepository;
import com.daun.word.domain.member.domain.repository.FakeMemberRepository;
import com.daun.word.domain.member.domain.repository.MemberRepository;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.problem.domain.repository.FakeProblemQueryRepository;
import com.daun.word.domain.problem.domain.repository.ProblemQueryRepository;
import com.daun.word.domain.problem.dto.search.ProblemSearchQuery;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.recommend.domain.repository.FakeRecommendRepository;
import com.daun.word.domain.recommend.domain.repository.RecommendRepository;
import com.daun.word.domain.recommend.domain.vo.RecommendType;
import com.daun.word.domain.recommend.dto.RecommendRequest;
import com.daun.word.global.infra.solvedac.FakeSolvedAcClient;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RecommendServiceTest {

    private RecommendService recommendService;

    private MemberRepository memberRepository;

    private RecommendRepository recommendRepository;

    private ProblemQueryRepository problemQueryRepository;

    private AssignmentRepository assignmentRepository;

    private MemberService memberService;

    private SolvedAcClient solvedAcClient;

    @BeforeEach
    void setUp() {
        //Fake DB
        this.memberRepository = new FakeMemberRepository();
        this.recommendRepository = new FakeRecommendRepository();
        this.problemQueryRepository = new FakeProblemQueryRepository();
        this.assignmentRepository = new FakeAssignmentRepository();
        //Service
        this.memberService = new MemberService(memberRepository, new BCryptPasswordEncoder());
        //infra
        this.solvedAcClient = new FakeSolvedAcClient();
        recommendService = new RecommendService(recommendRepository, memberService, solvedAcClient, problemQueryRepository, assignmentRepository);
    }

    @Test
    void findById() {
    }

    @DisplayName("문제를 추천한다")
    @Test
    void recommend() {
        //given
        ProblemSearchQuery query = new ProblemSearchQuery(11, 15);
        RecommendRequest request = new RecommendRequest(Arrays.asList(Fixture.daun9870jung().email()), RecommendType.TIER, query);
        //when
        Recommend recommend = recommendService.recommend(request).get(0);
        //then
        assertThat(recommend).isNotNull();
        assertAll(
                () -> assertThat(recommend.getId()).isInstanceOf(UUID.class),
                () -> assertThat(recommend.getType()).isEqualTo(request.getType()),
                () -> assertThat(recommend.getProblem()).isNotNull(),
                () -> assertThat(recommend.getProblem().getTier().getLevel()).isBetween(11, 15),
                () -> assertThat(recommend.getMember()).isNotNull(),
                () -> assertThat(recommend.getCreatedAt()).isNotNull(),
                () -> assertThat(recommend.getUpdatedAt()).isNotNull(),
                () -> assertThat(recommend.isDeleted()).isFalse()
        );
    }
}
