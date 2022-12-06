package com.daun.word.domain.reference.domain.repository;

import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.reference.domain.Reference;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReferenceRepository {

    Reference save(final Reference reference);

}
