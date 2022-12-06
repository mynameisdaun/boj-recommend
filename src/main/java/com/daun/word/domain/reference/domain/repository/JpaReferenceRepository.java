package com.daun.word.domain.reference.domain.repository;

import com.daun.word.domain.reference.domain.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaReferenceRepository extends ReferenceRepository, JpaRepository<Reference, UUID> {
}
