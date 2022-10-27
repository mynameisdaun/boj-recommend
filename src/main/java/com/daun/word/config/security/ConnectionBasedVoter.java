package com.daun.word.config.security;

import com.daun.word.global.GlobalId;
import com.daun.word.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.function.Function;

import static org.apache.commons.lang3.ClassUtils.isAssignable;

@RequiredArgsConstructor
public class ConnectionBasedVoter implements AccessDecisionVoter<FilterInvocation> {
    //TODO: API 마다 다양하게, 어떤 Voter를 선택할지 골라야 한다. 어떻게 적용할 수 있을까?
    private final RequestMatcher requiresAuthorizationRequestMatcher;

    private final Function<String, GlobalId<Member, Integer>> idExtractor;


    @Override
    public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {
        HttpServletRequest request = fi.getRequest();

        // 본인 자신
        return ACCESS_GRANTED;
//        if (!requiresAuthorization(request)) {
//            return ACCESS_GRANTED;
//        }
//
//        if (!JwtAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
//            return ACCESS_ABSTAIN;
//        }
//        JwtAuthentication jwtAuth = (JwtAuthentication) authentication.getPrincipal();
//        Id<Member, Integer> targetId = obtainTargetId(request);
    }

    private boolean requiresAuthorization(HttpServletRequest request) {
        return requiresAuthorizationRequestMatcher.matches(request);
    }

    private GlobalId<Member, Integer> obtainTargetId(HttpServletRequest request) {
        return idExtractor.apply(request.getRequestURI());
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return isAssignable(FilterInvocation.class, clazz);
    }

}
