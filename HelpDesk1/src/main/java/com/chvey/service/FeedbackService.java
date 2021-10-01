package com.chvey.service;

import com.chvey.domain.Feedback;
import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.mail.EmailTemplate;
import com.chvey.mail.MailSender;
import com.chvey.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MailSender mailSender;


    public Optional<Feedback> getFeedbackByTicketId(Long ticketId) {
        return feedbackRepository.findFeedbackByTicketId(ticketId);
    }

    public Feedback saveFeedback(Feedback feedback, String email) {
        User user = userService.getUserByEmail(email);
        Ticket ticket = feedback.getTicket();
        if (user.equals(ticket.getOwner())) {
            feedback.setUser(user);
            feedback.setDate(LocalDate.now());
            feedback.setId(feedbackRepository.save(feedback));
            List<User> recipients = new ArrayList<>();
            recipients.add(ticket.getAssignee());
            mailSender.sendAcceptableEmail(recipients, ticket.getId(), EmailTemplate.TICKET_WAS_PROVIDED);
            return feedback;
        } else {
            return null;
        }
    }
}
