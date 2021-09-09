package com.chvey.service;

import com.chvey.domain.Feedback;
import com.chvey.domain.User;
import com.chvey.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserService userService;

    public Feedback getFeedbackByTicketId(Long ticketId) {
        return feedbackRepository.findFeedbackByTicketId(ticketId);
    }

    public Feedback saveFeedback(Feedback feedback, String email) {
        User user = userService.getUserByEmail(email);
        feedback.setUser(user);
        feedback.setDate(LocalDate.now());
        feedback.setId(feedbackRepository.save(feedback));
        return feedback;
    }
}
