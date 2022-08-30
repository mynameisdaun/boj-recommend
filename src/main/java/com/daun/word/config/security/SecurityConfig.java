package com.daun.word.config.security;

import com.daun.word.commons.Id;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.Role;
import com.daun.word.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.math.NumberUtils.toLong;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final EntryPointUnauthorizedHandler unauthorizedHandler;
    private final Jwt jwt;
    private final JwtTokenConfigure jwtTokenConfigure;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public SecurityConfig
        (
          JwtAccessDeniedHandler accessDeniedHandler,
          EntryPointUnauthorizedHandler unauthorizedHandler,
          Jwt jwt,
          JwtTokenConfigure jwtTokenConfigure,
          AuthenticationManagerBuilder authenticationManagerBuilder,
          JwtAuthenticationProvider jwtAuthenticationProvider
        )
        {
          this.accessDeniedHandler = accessDeniedHandler;
          this.unauthorizedHandler = unauthorizedHandler;
          this.jwt = jwt;
          this.jwtTokenConfigure = jwtTokenConfigure;
          this.authenticationManagerBuilder = authenticationManagerBuilder;
          this.authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
        }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ConnectionBasedVoter connectionBasedVoter() {
        final String regex = "^/api/user/(\\d+)/post/.*$";
        final Pattern pattern = Pattern.compile(regex);
        RequestMatcher requiresAuthorizationRequestMatcher = new RegexRequestMatcher(pattern.pattern(), null);
        return new ConnectionBasedVoter(
                requiresAuthorizationRequestMatcher,
                (String url) -> {
                    /* url에서 targetId를 추출하기 위해 정규식 처리 */
                    Matcher matcher = pattern.matcher(url);
                    long id = matcher.matches() ? toLong(matcher.group(1), -1) : -1;
                    return Id.of(Member.class, (int) id);
                }
        );
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new WebExpressionVoter());
        // voter 목록에 connectionBasedVoter 를 추가함
        decisionVoters.add(connectionBasedVoter());
        // 모든 voter 승인해야 해야함
        return new UnanimousBased(decisionVoters);
    }

    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(jwtTokenConfigure.getHeader(), jwt);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .disable()
            .headers()
                .disable()
            .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/word/**").hasRole(Role.USER.name())
                .accessDecisionManager(accessDecisionManager())
            .anyRequest().permitAll()
            .and()
            .formLogin()
                .disable();
        http
            .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
