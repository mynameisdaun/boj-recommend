package com.daun.word.global;

import com.daun.word.domain.problem.domain.Problem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GlobalIdTest {


    @DisplayName(value = "equals and hashcode test")
    @Test
    void equalsAndHashCode() throws Exception {
        //given
        GlobalId<Problem, Integer> globalId_1 = GlobalId.of(Problem.class, 1);
        GlobalId<Problem, Integer> globalId_2 = GlobalId.of(Problem.class, 1);
        //when&&then
        assertThat(globalId_1.equals(globalId_2)).isTrue();
    }

    @DisplayName(value = "contains test")
    @Test
    void contains() throws Exception {
        //given
        List<GlobalId<Problem, Integer>> ids = new ArrayList<>();
        GlobalId<Problem, Integer> globalId_1 = GlobalId.of(Problem.class, 1);
        ids.add(globalId_1);
        //when&&then
        assertThat(ids.contains(GlobalId.of(Problem.class, 1))).isTrue();
    }

}
