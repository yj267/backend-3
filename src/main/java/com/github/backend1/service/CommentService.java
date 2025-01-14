package com.github.backend1.service;

import com.github.backend1.domain.Comment;
import com.github.backend1.dto.CommentRequest;
import com.github.backend1.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment createComment(CommentRequest request) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setAuthor(request.getWriterMemberId());
        return commentRepository.save(comment);
    }
}

