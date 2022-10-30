package com.daun.word.domain.member.domain.repository;

import com.daun.word.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemberJpaRepository extends MemberRepository, JpaRepository<Member, UUID> {

}
