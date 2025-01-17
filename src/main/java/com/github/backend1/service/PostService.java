package com.github.backend1.service;

import com.github.backend1.domain.Post;
import com.github.backend1.domain.User;
import com.github.backend1.repository.MemberRepository;
import com.github.backend1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Post writePost(String title, String body, Long writerMemberId) {
        User writer = memberRepository.findById(writerMemberId)
                .orElseThrow(() -> new IllegalStateException("Member not found for ID: " + writerMemberId));

        return postRepository.save(
                Post.builder()
                        .title(title)
                        .body(body)
                        .writer(writer)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
