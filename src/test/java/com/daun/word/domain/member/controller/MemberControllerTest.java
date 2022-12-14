package com.daun.word.domain.member.controller;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Role;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.member.dto.RegisterRequest;
import com.daun.word.domain.member.exception.DuplicateMemberException;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.member.service.RegisterService;
import com.daun.word.global.vo.Tier;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MemberController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class MemberControllerTest {

    private final String BASE_URL = "/member";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder encoder;

    @MockBean
    private MemberService memberService;

    @MockBean
    private RegisterService registerService;

    @DisplayName(value = "회원을 등록한다")
    @Test
    void register() throws Exception {
        //given
        RegisterRequest request = new RegisterRequest("new-email", "NewbeesPassword98$", "뉴비", SocialType.W.name());
        UUID id = UUID.randomUUID();
        Member unidentified = new Member(id, request.getEmail(), request.getName(), encoder.encode(request.getPassword().getPassword()), new Tier(11), request.getSocialType(), Role.ROLE_TEMP);
        given(registerService.register(request))
                .willReturn(Arrays.asList());
        //when&&then
        mockMvc.perform(post(BASE_URL)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(id.toString()))
                .andExpect(jsonPath("$.data.email").value("new-email"))
                .andExpect(jsonPath("$.data.name").value("뉴비"))
                .andExpect(jsonPath("$.data.tier.level").value(11));
    }

    @DisplayName(value = "중복된 회원은 가입할 수 없다")
    @Test
    void register_fail_duplicate_member() throws Exception {
        RegisterRequest request = new RegisterRequest("new-email", "NewbeesPassword98$", "뉴비", SocialType.W.name());
        given(registerService.register(request))
                .willThrow(new DuplicateMemberException("이미 가입한 회원입니다"));

        mockMvc.perform(post(BASE_URL)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().is(409))
                .andExpect(jsonPath("$.code").value(409))
                .andExpect(jsonPath("$.message").value("CONFLICT"));
    }
}
