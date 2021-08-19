package com.chvey.converters;

import com.chvey.domain.Ticket;
import com.chvey.domain.enums.State;
import com.chvey.domain.enums.Urgency;
import com.chvey.dto.TicketDto;
import com.chvey.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Component
public class TicketConverter {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserConverter userConverter;

    public TicketDto toDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setApprover(nonNull(ticket.getApprover()) ?
                userConverter.toDto(ticket.getApprover()) : null);
        ticketDto.setAssignee(nonNull(ticket.getAssignee()) ?
                userConverter.toDto(ticket.getAssignee()) : null);
        ticketDto.setCategory(ticket.getCategory().getId());
        ticketDto.setCreatedOn(ticket.getCreatedOn().toString());
        ticketDto.setDescription(ticket.getDescription());
        ticketDto.setName(ticket.getName());
        ticketDto.setOwner(userConverter.toDto(ticket.getOwner()));
        ticketDto.setDesiredResolutionDate(nonNull(ticket.getDesiredResolutionDate()) ?
                ticket.getDesiredResolutionDate().toString() : null);
        ticketDto.setState(ticket.getState().name());
        ticketDto.setUrgency(ticket.getUrgency().name());
        return ticketDto;
    }

    public Ticket toEntity(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setName(ticketDto.getName());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setState(State.valueOf(ticketDto.getState().toUpperCase()));
        ticket.setUrgency(Urgency.valueOf(ticketDto.getUrgency().toUpperCase()));
        ticket.setDesiredResolutionDate(nonNull(ticketDto.getDesiredResolutionDate()) ?
                LocalDate.parse(ticketDto.getDesiredResolutionDate()) : null);
        ticket.setCreatedOn(nonNull(ticketDto.getCreatedOn()) ?
                LocalDate.parse(ticketDto.getCreatedOn()) : null);
        ticket.setOwner(nonNull(ticketDto.getOwner()) ?
                userConverter.toEntity(ticketDto.getOwner()) : null);
        ticket.setApprover(nonNull(ticketDto.getApprover()) ?
                userConverter.toEntity(ticketDto.getApprover()) : null);
        ticket.setAssignee(nonNull(ticketDto.getAssignee()) ?
                userConverter.toEntity(ticketDto.getAssignee()) : null);
        ticket.setAssignee(ticket.getAssignee());
        ticket.setCategory(categoryService.getCategoryById(ticketDto.getCategory()));
        return ticket;
    }

    public static void main(String[] args) {
    LocalDateTime ld= LocalDateTime.now();
        String nn = "";

        System.out.println(ld);
    }
}
