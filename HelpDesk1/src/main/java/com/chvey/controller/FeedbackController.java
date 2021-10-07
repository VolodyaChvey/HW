package com.chvey.controller;

import com.chvey.converters.FeedbackConverter;
import com.chvey.domain.Feedback;
import com.chvey.dto.FeedbackDto;
import com.chvey.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/tickets")
public class FeedbackController {
    private FeedbackConverter feedbackConverter;
    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackConverter feedbackConverter, FeedbackService feedbackService) {
        this.feedbackConverter = feedbackConverter;
        this.feedbackService = feedbackService;
    }

    @GetMapping(value = "/{ticketId}/feedback")
    public ResponseEntity getFeedback(@PathVariable Long ticketId) {
        return feedbackService.getFeedbackByTicketId(ticketId)
                .map(value -> ResponseEntity.ok(feedbackConverter.toDto(value)))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{ticketId}/feedback")
    public ResponseEntity saveFeedback(@PathVariable("ticketId") long ticketId,
                                       @RequestBody FeedbackDto feedbackDto, Principal principal) {
        Feedback feedback = feedbackService
                .saveFeedback(feedbackConverter.toEntity(feedbackDto), principal.getName(), ticketId);
        return feedback != null
                ? new ResponseEntity(feedbackConverter.toDto(feedback), HttpStatus.CREATED)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
