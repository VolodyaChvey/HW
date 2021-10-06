package com.chvey.service;

import com.chvey.domain.Feedback;
import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.exceptions.FeedbackIsNotCreatedException;
import com.chvey.exceptions.TicketNotFoundException;
import com.chvey.mail.EmailTemplate;
import com.chvey.mail.MailSender;
import com.chvey.repository.FeedbackRepository;
import com.chvey.validators.FeedbackValidator;
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
    @Autowired
    private TicketService ticketService;
    @Autowired
    private FeedbackValidator feedbackValidator;


    public Optional<Feedback> getFeedbackByTicketId(Long ticketId) {
        return feedbackRepository.findFeedbackByTicketId(ticketId);
    }

    public Feedback saveFeedback(Feedback feedback, String email, Long ticketId) {
        try {
            User user = userService.getUserByEmail(email);
            Ticket ticket = ticketService.getTicketById(ticketId)
                    .orElseThrow(()->new TicketNotFoundException("Ticket is not found"));
            if (feedbackValidator.save(user, ticket, feedback)) {
                feedback.setUser(user);
                feedback.setTicket(ticket);
                feedback.setDate(LocalDate.now());
                feedback.setId(feedbackRepository.save(feedback)
                        .orElseThrow(() -> new FeedbackIsNotCreatedException("Feedback is not created for ticket " + ticket.getId())));
                mailing(ticket.getAssignee(), ticket.getId());
                return feedback;
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void mailing(User user, Long ticketId) {
        List<User> recipients = new ArrayList<>();
        recipients.add(user);
        mailSender.sendAcceptableEmail(recipients, ticketId, EmailTemplate.TICKET_WAS_PROVIDED);
    }
}
