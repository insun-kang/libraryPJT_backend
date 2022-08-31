package com.project.library.service;

import com.project.library.domain.Comment;
import com.project.library.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }

    public List<Comment> findComments(){
        return commentRepository.findAll();
    }

    public List<Comment> findCommentsBybookId(Long bookId){
        return commentRepository.findByBookId(bookId);
    }

    public Comment findOne(Long commentId){
        return commentRepository.findOne(commentId);
    }
}
