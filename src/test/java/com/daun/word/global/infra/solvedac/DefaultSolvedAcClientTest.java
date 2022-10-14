package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.Id;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.Tier;
import com.daun.word.global.vo.Title;
import com.daun.word.global.vo.URL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DefaultSolvedAcClientTest {

    private SolvedAcClient solvedAcClient;

    @BeforeEach
    public void setUp() {
        this.solvedAcClient = new DefaultSolvedAcClient(new RestTemplate());
    }

    @DisplayName(value = "Problem Id로 solvedAc에서 문제 조회")
    @Test
    void findById() throws Exception {
        //given&&when
        Problem problem = solvedAcClient.findById(Id.of(Problem.class, 16120));
        //then
        assertThat(problem).isNotNull();
        assertAll(
                () -> assertThat(problem.getId()).isEqualTo(16120),
                () -> assertThat(problem.getTitle()).isEqualTo(new Title("PPAP")),
                () -> assertThat(problem.getUrl()).isEqualTo(new URL("https://www.acmicpc.net/problem/16120")),
                () -> assertThat(problem.getTier()).isEqualTo(new Tier(12)),
                () -> assertThat(problem.getTags().size()).isEqualTo(4),
                () -> assertThat(problem.getTags().get(0).getId()).isPositive(),
                () -> assertThat(problem.getTags().get(0).getKey()).isNotNull(),
                () -> assertThat(problem.getTags().get(0).getTitle()).isNotNull()
        );
    }

    @DisplayName(value = "모든 회원이 풀지 않은 문제 solvedAc에서 조회")
    @Test
    void unSolvedProblemsByMembers() throws Exception {
        //given
        Member member1 = new Member(new Email("daun9870jung"), "example-password", new Name("autowired"), new Tier(15), SocialType.W);
        Member member2 = new Member(new Email("shp7724"), "example-password", new Name("autowired"), new Tier(15), SocialType.W);
        Member member3 = new Member(new Email("baggomsoon96"), "example-password", new Name("autowired"), new Tier(15), SocialType.W);
        Member member4 = new Member(new Email("pine98"), "example-password", new Name("autowired"), new Tier(15), SocialType.W);
        List<Member> members = Arrays.asList(member1, member4, member2, member3);
        Problem p1 = new Problem(1918, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        Problem p2 = new Problem(16120, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        Problem p3 = new Problem(1167, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        Problem p4 = new Problem(20149, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        Problem p5 = new Problem(2887, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        Problem p6 = new Problem(14003, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        Problem p7 = new Problem(1700, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        Problem p8 = new Problem(3665, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        Problem p9 = new Problem(1202, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        Problem p10 = new Problem(1655, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);

        Problem cp1 = new Problem(1845, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        Problem cp2 = new Problem(18236, new Title("t"), new URL("1"), new Tier(15), null, 0, 0);
        List<Problem> problems = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, cp1, cp2);
        //when
        List<Problem> unsolved = solvedAcClient.unSolvedProblemsByMembers(members, problems);
        assertAll(
                () -> assertThat(unsolved).contains(cp1, cp2),
                () -> assertThat(unsolved).doesNotContain(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
        );
        //then
    }
}
