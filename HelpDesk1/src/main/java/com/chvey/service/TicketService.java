package com.chvey.service;

import com.chvey.converters.TicketConverter;
import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.Role;
import com.chvey.domain.enums.State;
import com.chvey.mail.EmailTemplate;
import com.chvey.mail.MailSender;
import com.chvey.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
    @Autowired
    private MailSender mailSender;


    public Ticket createTicket(Ticket ticket, CommonsMultipartFile[] files, String email) {
        User user = userService.getUserByEmail(email);
        ticket.setOwner(user);
        ticket.setCreatedOn(LocalDate.now());
        ticket.setId(ticketRepository.saveTicket(ticket));
        historyService.createTicketHistory(ticket, user);
        attachmentService.save(files, ticket);
        if (ticket.getState() == State.NEW) {
            List<User> recipients = userService.getAllManagers().stream()
                    .filter(u -> u.getId() != user.getId())
                    .collect(Collectors.toList());
            mailSender.sendAcceptableEmail(recipients, ticket.getId(), EmailTemplate.NEW_TICKET_FOR_APPROVAL);
        }
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
        if (ticket.getState() == State.NEW) {
            List<User> recipients = userService.getAllManagers().stream()
                    .filter(u -> u.getId() != user.getId())
                    .collect(Collectors.toList());
            mailSender.sendAcceptableEmail(recipients, ticket.getId(), EmailTemplate.NEW_TICKET_FOR_APPROVAL);
        }
    }

    public void changeTicketState(Long id, String status, String email) {
        User user = userService.getUserByEmail(email);
        State stateNew = State.valueOf(status.toUpperCase());
        Ticket ticket = ticketRepository.findTicketById(id);
        if (checkStateNew(user, ticket, stateNew)) {
            historyService.statusChangedTicketHistory(ticket, user, stateNew);
            ticketRepository.updateStateTicket(ticket.getId(), stateNew);
            /*if (stateNew == State.APPROVED) {
                ticketRepository.addApproverTicket(ticket.getId(), user);
            }*/
           /* if (stateNew == State.IN_PROGRESS) {
                ticketRepository.addAssigneeticket(ticket.getId(), user);
            }*/
            switch (stateNew) {
                case NEW:
                    List<User> recipientsNew = userService.getAllManagers().stream()
                            .filter(u -> u.getId() != user.getId())
                            .collect(Collectors.toList());
                    mailSender.sendAcceptableEmail(recipientsNew, ticket.getId(), EmailTemplate.NEW_TICKET_FOR_APPROVAL);
                    break;
                case APPROVED:
                    ticketRepository.addApproverTicket(ticket.getId(), user);
                    List<User> recipientsApproved = userService.getAllEngineers();
                    recipientsApproved.add(ticket.getOwner());
                    mailSender.sendAcceptableEmail(recipientsApproved, ticket.getId(), EmailTemplate.TICKET_WAS_APPROVED);
                    break;
                case DECLINED:
                    List<User> recipientsDeclined = new ArrayList<>();
                    recipientsDeclined.add(ticket.getOwner());
                    mailSender.sendAcceptableEmail(recipientsDeclined, ticket.getId(), EmailTemplate.TICKET_WAS_DECLINED);
                    break;
                case CANCELED:
                    if (user.getRole() == Role.MANAGER) {
                        List<User> recipientsCanceledManager = new ArrayList<>();
                        recipientsCanceledManager.add(ticket.getOwner());
                        mailSender.sendAcceptableEmail(recipientsCanceledManager, ticket.getId(),
                                EmailTemplate.TICKET_WAS_CANCELLED_MANAGER);
                        break;
                    }
                    if (user.getRole() == Role.ENGINEER) {
                        List<User> recipientsCanceledEngineer = new ArrayList<>();
                        recipientsCanceledEngineer.add(ticket.getOwner());
                        recipientsCanceledEngineer.add(ticket.getApprover());
                        mailSender.sendAcceptableEmail(recipientsCanceledEngineer, ticket.getId(),
                                EmailTemplate.TICKET_WAS_CANCELLED_ENGINEER);
                        break;
                    }
                case IN_PROGRESS:
                    ticketRepository.addAssigneeticket(ticket.getId(), user);
                    break;
                case DONE:
                    List<User> recipientsDone = new ArrayList<>();
                    recipientsDone.add(ticket.getOwner());
                    mailSender.sendAcceptableEmail(recipientsDone, ticket.getId(), EmailTemplate.TICKET_WAS_DONE);
                    break;
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
