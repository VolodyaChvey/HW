package com.chvey.converters;

import com.chvey.domain.Ticket;
import com.chvey.dto.TicketDto;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {


    public TicketDto toDto (Ticket ticket){
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setApprover(ticket.getApprover());
        ticketDto.setAssignee(ticket.getAssignee());
        ticketDto.setCategory(ticket.getCategory());
        ticketDto.setCreatedOn(ticket.getCreatedOn());
        ticketDto.setDescription(ticket.getDescription());
        ticketDto.setName(ticket.getName());
        ticketDto.setOwner(ticket.getOwner());
        ticketDto.setDesiredResolutionDate(ticket.getDesiredResolutionDate());
        ticketDto.setState(ticket.getState());
        ticketDto.setUrgency(ticket.getUrgency());
        return ticketDto;
    }
}
