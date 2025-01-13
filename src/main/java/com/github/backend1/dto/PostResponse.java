package com.github.backend1.dto;

import com.github.backend1.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor // Builder 사용시 꼭 같이 사용
@Getter
public class PostResponse{ //글 이렇게 작성됐다고 응답하는 dto
    private Long postId;
    private Long writerMemberId;
    private String title;
    private String body;
    private LocalDateTime createdAt; //글 작성된 시간

    public static PostResponse from(Post post){
        //builder패턴을 통해 post 엔티티를 PostResponse로 변환하는 static함수
        //post로부터 PostResponse 만드므로 from 메소드 정의
        return PostResponse.builder()
                .postId(post.getId())
                .writerMemberId(post.getWriter().getId())
                .title(post.getTitle())
                .body(post.getBody())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
