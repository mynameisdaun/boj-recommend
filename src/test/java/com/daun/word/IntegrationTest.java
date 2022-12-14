package com.daun.word;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


public class IntegrationTest {

    @DisplayName(value = ".")
    @Test
    void t() throws Exception {
        //given
        String d= "Wjdekdns1!";
        //when
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //then
        String encode = passwordEncoder.encode(d);
        System.out.println(encode);

    }
}
