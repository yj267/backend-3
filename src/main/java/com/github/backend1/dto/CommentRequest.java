package com.github.backend1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String content;
    private String writerMemberId;
    private Long postId;
}