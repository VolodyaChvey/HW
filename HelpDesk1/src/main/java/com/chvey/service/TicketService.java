package com.chvey.service;

import com.chvey.converters.TicketConverter;
import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.Role;
import com.chvey.domain.enums.State;
import com.chvey.dto.TicketDto;
import com.chvey.exceptions.RecipientsException;
import com.chvey.exceptions.TicketNotFoundException;
import com.chvey.exceptions.UserNotFoundException;
import com.chvey.mail.EmailTemplate;
import com.chvey.mail.MailSender;
import com.chvey.repository.TicketRepository;
import com.chvey.validators.AttachmentValidator;
import com.chvey.validators.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;


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
    @Autowired
    private TicketValidator ticketValidator;
    @Autowired
    private AttachmentValidator attachmentValidator;


    public Optional<Ticket> createTicket(TicketDto ticketDto, CommonsMultipartFile[] files, Principal principal) {
        try {
            if (ticketValidator.dto(ticketDto) && attachmentValidator.save(files)) {
                Ticket ticket = ticketConverter.toEntity(ticketDto);
                User user = userService.getCurrentUser(principal)
                        .orElseThrow(() -> new UserNotFoundException("User not found " + principal.getName()));
                ticket.setOwner(user);
                ticket.setCreatedOn(LocalDate.now());
                Long ticketId = ticketRepository.saveTicket(ticket);
                if (nonNull(ticketId)) {
                    ticket.setId(ticketRepository.saveTicket(ticket));
                    historyService.createTicketHistory(ticket, user);
                    attachmentService.save(files, ticket);
                    if (ticket.getState() == State.NEW) {
                        try {
                            List<User> recipients = userService.getAllManagers()
                                    .map(value -> value.stream()
                                            .filter(u -> u.getId() != user.getId())
                                            .collect(Collectors.toList()))
                                    .orElseThrow(() -> new RecipientsException("Managers not found"));
                            mailSender.sendAcceptableEmail(recipients, ticket.getId(), EmailTemplate.NEW_TICKET_FOR_APPROVAL);
                        } catch (RecipientsException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    return Optional.of(ticket);
                }
            }
            return Optional.empty();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<Ticket>> getTicketsByUserId(Principal principal) {
        User user = userService.getCurrentUser(principal)
                .orElseThrow(() -> new UserNotFoundException("User not found " + principal.getName()));
        if (user.getRole() == Role.EMPLOYEE) {
            return Optional.ofNullable(ticketRepository.findTicketsByUserId(user.getId()));
        } else if (user.getRole() == Role.MANAGER) {
            return Optional.ofNullable(ticketRepository.findAllTicketsManager(user.getId()));
        } else {
            return Optional.ofNullable(ticketRepository.findTicketsByAssigneeId(user.getId()));
        }
    }

    public Optional<Ticket> getTicketById(Long id) {
        return Optional.ofNullable(ticketRepository.findTicketById(id));
    }

    public boolean editTicket(Principal principal, TicketDto ticketDto) {
        boolean result = false;
        try {
            if (ticketValidator.dto(ticketDto)) {
                Ticket ticket = ticketConverter.toEntity(ticketDto);
                result = ticketRepository.updateTicket(ticket);
                User user = userService.getCurrentUser(principal)
                        .orElseThrow(() -> new UserNotFoundException("User not found " + principal.getName()));
                historyService.editTicketHistory(ticket, user);
                if (ticket.getState() == State.NEW) {
                    List<User> recipients = userService.getAllManagers()
                            .map(value -> value.stream()
                                    .filter(u -> u.getId() != user.getId())
                                    .collect(Collectors.toList()))
                            .orElseThrow(() -> new RecipientsException("Managers not found"));
                    mailSender.sendAcceptableEmail(recipients, ticket.getId(), EmailTemplate.NEW_TICKET_FOR_APPROVAL);
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean changeTicketState(Long id, String status, Principal principal) {
        boolean result = false;
        try {
            User user = userService.getCurrentUser(principal)
                    .orElseThrow(() -> new UserNotFoundException("User not found " + principal.getName()));
            State stateNew = State.valueOf(status.toUpperCase());
            Ticket ticket = getTicketById(id)
                    .orElseThrow(() -> new TicketNotFoundException("Ticket is not found"));
            if (ticketValidator.checkStateNew(user, ticket, stateNew)) {
                result = ticketRepository.updateStateTicket(ticket.getId(), stateNew);
                historyService.statusChangedTicketHistory(ticket, user, stateNew);
                switch (stateNew) {
                    case NEW:
                        List<User> recipientsNew = userService.getAllManagers()
                                .map(value -> value.stream()
                                        .filter(u -> u.getId() != user.getId())
                                        .collect(Collectors.toList()))
                                .orElseThrow(() -> new UserNotFoundException("Managers not found"));
                        mailSender.sendAcceptableEmail(recipientsNew, ticket.getId(), EmailTemplate.NEW_TICKET_FOR_APPROVAL);
                        break;
                    case APPROVED:
                        ticketRepository.addApproverTicket(ticket.getId(), user);
                        List<User> recipientsApproved = userService.getAllEngineers()
                                .orElseThrow(() -> new UserNotFoundException("Engineers not found"));
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
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
}
