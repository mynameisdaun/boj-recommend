package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.recommend.dto.search.RecommendSearchQuery;
import com.daun.word.domain.recommend.dto.search.SortDirection;
import com.daun.word.domain.recommend.dto.search.SortType;
import com.daun.word.global.vo.Tier;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.daun.word.domain.problem.domain.QProblem.problem;

@RequiredArgsConstructor
@Repository
public class ProblemQueryRepositoryImpl implements ProblemQueryRepository {

    private final JPAQueryFactory factory;

    @Override
    public List<Problem> search(RecommendSearchQuery query) {
        return factory
                .selectFrom(problem)
                .where(
                        tierGoe(query.getMinTier()),
                        tierLoe(query.getMaxTier())
                )
                .orderBy(order(query))
                .fetch();
    }

    private BooleanExpression tierGoe(Tier tier) {
        return tier != null ? problem.tier.level.goe(tier.getLevel()) : null;
    }

    private BooleanExpression tierLoe(Tier tier) {
        return tier != null ? problem.tier.level.loe(tier.getLevel()) : null;
    }

    private OrderSpecifier<Integer> order(RecommendSearchQuery query) {
        if (query.getDirection() == SortDirection.ASC) {
            if (query.getSort() == SortType.ACCEPTED_USER_COUNT) {
                return problem.acceptedUserCount.asc();
            }
        } else {
            if (query.getSort() == SortType.ACCEPTED_USER_COUNT) {
                return problem.acceptedUserCount.desc();
            }
        }
        return problem.acceptedUserCount.desc();
    }
}
