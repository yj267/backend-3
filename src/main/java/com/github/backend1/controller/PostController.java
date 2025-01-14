package com.github.backend1.controller;

import com.github.backend1.domain.Post;
import com.github.backend1.dto.AuthInfo;
import com.github.backend1.dto.PostRequest;
import com.github.backend1.dto.PostResponse;
import com.github.backend1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/posts") //경로
@RequiredArgsConstructor

public class PostController { //dto대로 controller 정의

    private final PostService postService;

    @PostMapping //행동
    public ResponseEntity<PostResponse> writePost( //글을 작성하는 메소드
                                                   AuthInfo authInfo, //AuthInfo를 파라미터로 주입 받음
                                                   @RequestBody PostRequest postRequest //요청할때 넣을 postRequest
    ){
        Post post = postService.writePost(postRequest.getTitle(),postRequest.getBody(),authInfo.getMemberId());
        return ResponseEntity.ok(PostResponse.from(post));//글 작성하고 작성된 결과를 response로 바꿔 응답
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(AuthInfo authInfo){
        return ResponseEntity.ok(
                postService.getAllPosts().stream()
                        .map(PostResponse::from)
                        .collect(Collectors.toList())
        );
    }

}
