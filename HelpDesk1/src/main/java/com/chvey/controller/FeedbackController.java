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
import java.util.Optional;

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
        Optional<Feedback> feedback = feedbackService.getFeedbackByTicketId(ticketId);
        if (feedback.isPresent()) {
            return ResponseEntity.ok(feedbackConverter.toDto(feedback.get()));
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{ticketId}/feedback")
    public ResponseEntity saveFeedback(@RequestBody FeedbackDto feedbackDto, Principal principal) {
        feedbackService.saveFeedback(feedbackConverter.toEntity(feedbackDto), principal.getName());
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
