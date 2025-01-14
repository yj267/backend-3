package com.github.backend1.service;

import com.github.backend1.domain.Post;
import com.github.backend1.repository.MemberRepository;
import com.github.backend1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Post writePost(String title, String body, Long writerMemberId){
        Member writer = memberRepository.findById(writerMemberId).orElseThrow(IllegalStateException);
        //memberRepository에서 writeMemberID(JWT)로 Member 먼저 찾아오기
        return postRepository.save( //Post 엔티티를 만들어서 저장
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
