package com.chvey.service;

import com.chvey.domain.Comment;
import com.chvey.domain.User;
import com.chvey.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;

    public List<Comment> getCommentsByTicketId(Long id) {
        return commentRepository.findCommentsByTicketId(id);
    }
    public Long saveComment(Comment comment,String email){
        User user = userService.getUserByEmail(email);
        comment.setDate(LocalDateTime.now());
        return commentRepository.saveComment(comment);
    }
}
