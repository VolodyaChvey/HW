package com.chvey.controller;

import com.chvey.converters.CommentConverter;
import com.chvey.dto.CommentDto;
import com.chvey.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(commentService.getCommentsByTicketId(ticketId)
                .stream().map(commentConverter::toDto).collect(Collectors.toList()));
    }

    @PostMapping(value = "/{ticketId}/comments")
    public ResponseEntity saveComment(@RequestBody CommentDto commentDto, Principal principal) {
        return ResponseEntity.ok(commentConverter.toDto(
                commentService.saveComment(commentConverter.toEntity(commentDto), principal.getName())));
    }
}
