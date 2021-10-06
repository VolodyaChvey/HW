package com.chvey.service;

import com.chvey.domain.Attachment;
import com.chvey.domain.History;
import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.State;
import com.chvey.exceptions.HistoryIsNotCreatedException;
import com.chvey.repository.HistoryRepository;
import com.chvey.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public Optional<List<History>> getHistoriesByTicketId(Long id) {
        return Optional.ofNullable(historyRepository.findHistoriesByTicketId(id));
    }

    public void createTicketHistory(Ticket ticket, User user) throws HistoryIsNotCreatedException {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription("Ticket is created");
        history.setAction("Ticket is created");
        if (!nonNull(historyRepository.saveHistory(history))) {
            throw new HistoryIsNotCreatedException("History is not created");
        }
    }

    public void editTicketHistory(Ticket ticket, User user) throws HistoryIsNotCreatedException {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription("Ticket is edited");
        history.setAction("Ticket is edited");
        if (!nonNull(historyRepository.saveHistory(history))) {
            throw new HistoryIsNotCreatedException("History is not created");
        }
        if (ticket.getState() == State.NEW) {
            statusChangedTicketEdit(ticket, user);
        }
    }

    public void statusChangedTicketHistory(Ticket ticket, User user, State stateNew)
            throws HistoryIsNotCreatedException {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription(String.format("Ticket istatus is changed from '%s' to '%s'",
                ticket.getState().name(), stateNew.name()));
        history.setAction("Ticket status is changed");
        if (!nonNull(historyRepository.saveHistory(history))) {
            throw new HistoryIsNotCreatedException("History is not created");
        }
    }

    private void statusChangedTicketEdit(Ticket ticket, User user) throws HistoryIsNotCreatedException {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription("Ticket istatus is changed from 'DRAFT' to 'NEW'");
        history.setAction("Ticket status is changed");
        if (!nonNull(historyRepository.saveHistory(history))) {
            throw new HistoryIsNotCreatedException("History is not created");
        }
    }

    public void addAttachment(Attachment attachment) throws HistoryIsNotCreatedException {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(attachment.getTicket());
        history.setUser(attachment.getTicket().getOwner());
        history.setAction("File is attached");
        history.setDescription(String.format("File is attached: %s", attachment.getName()));
        if (!nonNull(historyRepository.saveHistory(history))) {
            throw new HistoryIsNotCreatedException("History is not created");
        }
    }

    public void removeAttachment(Attachment attachment) throws HistoryIsNotCreatedException {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(attachment.getTicket());
        history.setUser(attachment.getTicket().getOwner());
        history.setAction("File is removed");
        history.setDescription(String.format("File is removed: %s", attachment.getName()));
        if (!nonNull(historyRepository.saveHistory(history))) {
            throw new HistoryIsNotCreatedException("History is not created");
        }
    }
}

