package com.chvey.validators;

import com.chvey.domain.Feedback;
import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.State;
import com.chvey.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackValidator {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private TextValidator textValidator;

    public boolean save(User user, Ticket ticket, Feedback feedback) {
        return user.equals(ticket.getOwner()) && ticket.getState() == State.DONE
                && !feedbackService.getFeedbackByTicketId(ticket.getId()).isPresent()
                && validFeedback(feedback);
    }

    private boolean validFeedback(Feedback feedback) {
        return (feedback.getRate() > 0 && feedback.getRate() < 6) &&
                (feedback.getText() == null || textValidator.text(feedback.getText()));
    }
}
