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
    public Comment saveComment(Comment comment,String email){
        User user = userService.getUserByEmail(email);
        comment.setUser(user);
        comment.setDate(LocalDateTime.now());
        comment.setId(commentRepository.saveComment(comment));
        return comment;
    }
}
