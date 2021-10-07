package com.chvey.controller;

import com.chvey.converters.CommentConverter;
import com.chvey.dto.CommentDto;
import com.chvey.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class CommentController {
    private CommentService commentService;
    private CommentConverter commentConverter;

    @Autowired
    public CommentController(CommentService commentService, CommentConverter commentConverter) {
        this.commentService = commentService;
        this.commentConverter = commentConverter;
    }

    @GetMapping(value = "/{ticketId}/comments")
    public ResponseEntity getComments(@PathVariable Long ticketId) {
        return commentService.getCommentsByTicketId(ticketId)
                .map(value -> ResponseEntity.ok(value
                        .stream().map(commentConverter::toDto).collect(Collectors.toList())))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{ticketId}/comments")
    public ResponseEntity saveComment(@PathVariable Long ticketId, @RequestBody CommentDto commentDto, Principal principal) {
        return commentService.saveComment(commentDto, principal, ticketId)
                .map(comment -> ResponseEntity.ok(commentConverter.toDto(comment)))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
