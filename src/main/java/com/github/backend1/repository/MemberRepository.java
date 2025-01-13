package com.github.backend1.repository;

import java.util.Optional;

public interface MemberRepository {
    Optional<Object> findById(Long writerMemberId);
}
