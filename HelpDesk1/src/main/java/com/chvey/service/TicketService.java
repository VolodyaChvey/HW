package com.chvey.service;

import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.Role;
import com.chvey.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getTicketsByUserId(User user) {
        if (user.getRole() == Role.EMPLOYEE) {
            return ticketRepository.findTicketsByUserId(user.getId());
        } else if (user.getRole() == Role.MANAGER) {
            List<Ticket> tickets = ticketRepository.findTicketsByUserId(user.getId());
            tickets.addAll(ticketRepository.findTicketsByApproverId(user.getId()));
            tickets.addAll(ticketRepository.findTicketsByStateNew());
            return tickets;
        } else {
            List<Ticket> tickets = ticketRepository.findTicketsByAssigneeId(user.getId());
            tickets.addAll(ticketRepository.findTicketsByStateApproved());
            return tickets;
        }
    }
}
