package com.chvey.converters;

import com.chvey.domain.Feedback;
import com.chvey.dto.FeedbackDto;
import com.chvey.exceptions.TicketNotFoundException;
import com.chvey.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.util.Objects.nonNull;

@Component
public class FeedbackConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private TicketService ticketService;

    public FeedbackDto toDto(Feedback feedback) {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setId(feedback.getId());
        feedbackDto.setRate(feedback.getRate());
        feedbackDto.setText(feedback.getText());
        feedbackDto.setDate(feedback.getDate().toString());
        feedbackDto.setUserDto(userConverter.toDto(feedback.getUser()));
        feedbackDto.setTicketId(feedback.getTicket().getId());
        return feedbackDto;
    }

    public Feedback toEntity(FeedbackDto feedbackDto) throws TicketNotFoundException{
        Feedback feedback = new Feedback();
        feedback.setId(feedbackDto.getId());
        feedback.setRate(feedbackDto.getRate());
        feedback.setText(feedbackDto.getText());
        feedback.setDate(nonNull(feedbackDto.getDate()) ? LocalDate.parse(feedbackDto.getDate()) : null);
        feedback.setUser(nonNull(feedbackDto.getUserDto()) ? userConverter.toEntity(feedbackDto.getUserDto()) : null);
        feedback.setTicket(ticketService.getTicketById(feedbackDto.getTicketId())
                .orElseThrow(()->new TicketNotFoundException("Ticket is not found")));
        return feedback;
    }
}
