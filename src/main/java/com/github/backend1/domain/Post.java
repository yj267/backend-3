package com.github.backend1.domain;

import org.springframework.web.ErrorResponse;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post { //필요한 스키마 정보들을 저장한 Post 엔티티 정의
    @Id //PK지정
    @GeneratedValue // 값을 지정해주지 않아도 DB에서 알아서 지정해줌
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_member_id")
    private Member writer;

    private String title;

    private String body;
    private LocalDateTime createdAt;

    public static ErrorResponse.Builder builder() {
    }

    public Long getId() {
    }

    public Post getWriter() {
    }

    public String getTitle() {
    }

    public String getBody() {
    }

    public LocalDateTime getCreatedAt() {
    }
}
