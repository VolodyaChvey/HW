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

    public List<Ticket> getTicketsByUserId(String email) {
        User user = userService.getUserByEmail(email);
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

    public List<Ticket> getMyTickets(String email){
        User user= userService.getUserByEmail(email);
        if(user.getRole()==Role.EMPLOYEE){
            return ticketRepository.findTicketsByUserId(user.getId());
        }else if (user.getRole()==Role.MANAGER){
            return ticketRepository.findMyTicketsManager(user.getId());
        }
        return ticketRepository.findTicketsByAssigneeId(user.getId());
    }
    public Ticket getSaveTicket(String email,Ticket ticket){
        User user = userService.getUserByEmail(email);
        ticket.setOwner(user);
        ticket.setCreatedOn(LocalDate.now());

        ticket.setId(ticketRepository.saveTicket(ticket));

        return ticket;
    }
}
