package com.chvey.service;

import com.chvey.domain.History;
import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.State;
import com.chvey.repository.HistoryRepository;
import com.chvey.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public List<History> getHistoriesByTicketId(Long id) {
        return historyRepository.findHistoriesByTicketId(id);
    }

    public Long createTicketHistory(Ticket ticket, User user) {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription("Ticket is created");
        history.setAction("Ticket is created");
        return historyRepository.saveHistory(history);
    }

    public void editTicketHistory(Ticket ticket, User user) {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription("Ticket is edited");
        history.setAction("Ticket is edited");
        historyRepository.saveHistory(history);
        if (ticket.getState() == State.NEW) {
            statusChangedTicketEdit(ticket, user);
        }
    }

    public Long statusChangedTicketHistory(Ticket ticket, User user, State stateNew) {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription(String.format("Ticket istatus is changed from '%s' to '%s'",
                ticket.getState().name(), stateNew.name()));
        history.setAction("Ticket status is changed");
        return historyRepository.saveHistory(history);
    }

    private Long statusChangedTicketEdit(Ticket ticket, User user) {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription("Ticket istatus is changed from 'DRAFT' to 'NEW'");
        history.setAction("Ticket status is changed");
        return historyRepository.saveHistory(history);
    }
}

