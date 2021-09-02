package com.chvey.service;

import com.chvey.converters.TicketConverter;
import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.Role;
import com.chvey.domain.enums.State;
import com.chvey.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
    @Autowired
    private TicketConverter ticketConverter;
    @Autowired
    private AttachmentService attachmentService;


    public Ticket createTicket(Ticket ticket, CommonsMultipartFile[] files, String email){
        User user = userService.getUserByEmail(email);
        ticket.setOwner(user);
        ticket.setCreatedOn(LocalDate.now());
        ticket.setId(ticketRepository.saveTicket(ticket));
        historyService.createTicketHistory(ticket, user);
        attachmentService.save(files,ticket);
        return ticket;
    }

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
        historyService.createTicketHistory(ticket, user);
        return ticket;
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findTicketById(id);
    }

    public void editTicket(String email, Ticket ticket) {
        User user = userService.getUserByEmail(email);
        historyService.editTicketHistory(ticket, user);
        ticketRepository.updateTicket(ticket);
    }

    public void changeTicketState(Long id, String status, String email) {
        User user = userService.getUserByEmail(email);
        State stateNew = State.valueOf(status.toUpperCase());
        Ticket ticket = ticketRepository.findTicketById(id);
        if (checkStateNew(user, ticket, stateNew)) {
            historyService.statusChangedTicketHistory(ticket, user, stateNew);
            ticketRepository.updateStateTicket(ticket.getId(), stateNew);
            if (stateNew == State.APPROVED) {
                ticketRepository.addApproverTicket(ticket.getId(), user);
            }
            if (stateNew == State.IN_PROGRESS) {
                ticketRepository.addAssigneeticket(ticket.getId(), user);
            }
        }
    }

    private boolean checkStateNew(User user, Ticket ticket, State stateNew) {
        boolean result = false;
        switch (stateNew) {
            case NEW:
                if (ticket.getState() == State.DRAFT || ticket.getState() == State.DECLINED) {
                    if ((user.getRole() == Role.EMPLOYEE || user.getRole() == Role.MANAGER)
                            && ticket.getOwner().getId() == user.getId()) {
                        result = true;
                    }
                }
                break;
            case APPROVED:
                if (ticket.getState() == State.NEW) {
                    if (user.getRole() == Role.MANAGER && ticket.getOwner().getId() != user.getId()) {
                        result = true;
                    }
                }
                break;
            case DECLINED:
                if (ticket.getState() == State.NEW) {
                    if (user.getRole() == Role.MANAGER && ticket.getOwner().getId() != user.getId()) {
                        result = true;
                    }
                }
                break;
            case CANCELED:
                if (ticket.getState() == State.DRAFT || ticket.getState() == State.DECLINED) {
                    if ((user.getRole() == Role.EMPLOYEE || user.getRole() == Role.MANAGER)
                            && ticket.getOwner().getId() == user.getId()) {
                        result = true;
                    }
                }
                if (ticket.getState() == State.NEW) {
                    if (user.getRole() == Role.MANAGER && ticket.getOwner().getId() != user.getId()) {
                        result = true;
                    }
                }
                if (ticket.getState() == State.APPROVED) {
                    if (user.getRole() == Role.ENGINEER) {
                        result = true;
                    }
                }
            case IN_PROGRESS:
                if (ticket.getState() == State.APPROVED) {
                    if (user.getRole() == Role.ENGINEER) {
                        result = true;
                    }
                }
            case DONE:
                if (ticket.getState() == State.IN_PROGRESS) {
                    if (user.getRole() == Role.ENGINEER) {
                        result = true;
                    }
                }
        }
        return result;
    }

}
