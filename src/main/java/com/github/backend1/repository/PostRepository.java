package com.github.backend1.repository;

import com.github.backend1.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends com.github.backend1.repository.JpaRepository<Post,Long> {
}
