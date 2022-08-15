package com.daun.word.member;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class DBConnectTestController {

    private final TestDAO testDAO;

    @GetMapping("/db")
    public List<TestDTO> HelloWorld() {
        return testDAO.getTestData();
    }
}
