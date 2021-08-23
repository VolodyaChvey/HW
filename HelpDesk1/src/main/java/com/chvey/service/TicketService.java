package com.chvey.service;

import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.Role;
import com.chvey.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private HistoryService historyService;

    public List<Ticket> getTicketsByUserId(String email) {
        User user = userService.getUserByEmail(email);
        if (user.getRole() == Role.EMPLOYEE) {
            return ticketRepository.findTicketsByUserId(user.getId());
        } else if (user.getRole() == Role.MANAGER) {
            return ticketRepository.findAllTicketsManager(user.getId());
        } else {
            return ticketRepository.findTicketsByAssigneeId(user.getId());
        }
    }

    public List<Ticket> getMyTickets(String email) {
        User user = userService.getUserByEmail(email);
        if (user.getRole() == Role.EMPLOYEE) {
            return ticketRepository.findTicketsByUserId(user.getId());
        } else if (user.getRole() == Role.MANAGER) {
            return ticketRepository.findMyTicketsManager(user.getId());
        }
        return ticketRepository.findTicketsByAssigneeId(user.getId());
    }

    public Ticket getSaveTicket(String email, Ticket ticket) {
        User user = userService.getUserByEmail(email);
        ticket.setOwner(user);
        ticket.setCreatedOn(LocalDate.now());
        ticket.setId(ticketRepository.saveTicket(ticket));
        historyService.createTicketHistory(ticket,user);
        return ticket;
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findTicketById(id);
    }
    public void editTicket(String email, Ticket ticket){
       User user = userService.getUserByEmail(email);
       // ticketRepository.findTicketById(ticket.getId());
        historyService.editTicketHistory(ticket, user);
        ticketRepository.updateTicket(ticket);
    }
}
