package com.github.backend1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.github.backend1.domain.User;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post { //필요한 스키마 정보들을 저장한 Post 엔티티 정의
    @jakarta.persistence.Id
    @Id //PK지정
    @GeneratedValue // 값을 지정해주지 않아도 DB에서 알아서 지정해줌
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_member_id")
    private User writer;

    private String title;

    private String body;
    private LocalDateTime createdAt;

}
