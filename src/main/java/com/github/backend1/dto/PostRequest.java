package com.github.backend1.dto;
import lombok.Getter;

@Getter // response body로 받았을때 title,body 꺼내와야하므로 getter
public class PostRequest { // 글 작성하는 요청
    private String title;
    private String body;
}
