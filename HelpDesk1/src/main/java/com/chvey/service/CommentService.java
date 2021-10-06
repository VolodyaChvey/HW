package com.chvey.service;

import com.chvey.converters.CommentConverter;
import com.chvey.domain.Comment;
import com.chvey.domain.User;
import com.chvey.dto.CommentDto;
import com.chvey.exceptions.UserNotFoundException;
import com.chvey.repository.CommentRepository;
import com.chvey.validators.CommentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentValidator commentValidator;
    @Autowired
    private CommentConverter commentConverter;

    public Optional<List<Comment>> getCommentsByTicketId(Long id) {
        return Optional.ofNullable(commentRepository.findCommentsByTicketId(id));
    }

    public Optional<Comment> saveComment(CommentDto commentDto, Principal principal, Long ticketId) {
        if (commentValidator.save(commentDto)) {
            commentDto.setTicketId(ticketId);
            Comment comment = commentConverter.toEntity(commentDto);
            User user = userService.getCurrentUser(principal)
                    .orElseThrow(()->new UserNotFoundException("User not found " + principal.getName()));
            comment.setUser(user);
            comment.setDate(LocalDateTime.now());
            Long commentId = commentRepository.saveComment(comment);
            if (nonNull(commentId)) {
                comment.setId(commentId);
                return Optional.of(comment);
            }
        }
        return Optional.empty();
    }
}
