package com.chvey.converters;

import com.chvey.domain.Comment;
import com.chvey.dto.CommentDto;
import com.chvey.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Component
public class CommentConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private TicketService ticketService;

    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setDate(comment.getDate().toString());
        commentDto.setText(comment.getText());
        commentDto.setTicketId(comment.getTicket().getId());
        commentDto.setUserDto(userConverter.toDto(comment.getUser()));
        return commentDto;
    }

    public Comment toEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setUser(nonNull(commentDto.getUserDto()) ? userConverter.toEntity(commentDto.getUserDto()) : null);
        comment.setTicket(ticketService.getTicketById(commentDto.getTicketId()));
        comment.setDate(nonNull(commentDto.getDate()) ? LocalDateTime.parse(commentDto.getDate()) : null);
        return comment;
    }
}
