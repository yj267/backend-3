package com.github.backend1.repository;

import com.github.backend1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long writerMemberId);
}
