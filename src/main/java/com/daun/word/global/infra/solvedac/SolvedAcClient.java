package com.daun.word.global.infra.solvedac;

import com.daun.word.global.Id;
import com.daun.word.domain.problem.domain.Problem;

public interface SolvedAcClient {

    Problem findById(Id<Problem, Integer> id);

}
