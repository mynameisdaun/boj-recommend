package com.daun.word.infra.solvedac;

import com.daun.word.global.Id;
import com.daun.word.problem.domain.Problem;

public interface SolvedAcClient {

    Problem findById(Id<Problem, Integer> id);

}
