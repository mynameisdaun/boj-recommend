package com.daun.word.global;

import com.daun.word.domain.problem.domain.Problem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IdTest {


    @DisplayName(value = "equals and hashcode test")
    @Test
    void equalsAndHashCode() throws Exception {
        //given
        Id<Problem, Integer> id_1 = Id.of(Problem.class, 1);
        Id<Problem, Integer> id_2 = Id.of(Problem.class, 1);
        //when&&then
        assertThat(id_1.equals(id_2)).isTrue();
    }

    @DisplayName(value = "contains test")
    @Test
    void contains() throws Exception {
        //given
        List<Id<Problem, Integer>> ids = new ArrayList<>();
        Id<Problem, Integer> id_1 = Id.of(Problem.class, 1);
        ids.add(id_1);
        //when&&then
        assertThat(ids.contains(Id.of(Problem.class, 1))).isTrue();
    }

}
