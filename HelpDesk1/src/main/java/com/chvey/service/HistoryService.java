package com.chvey.service;

import com.chvey.domain.History;
import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.State;
import com.chvey.repository.HistoryRepository;
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

    public List<History> getHistoriesByTicketId(Long id) {
        return historyRepository.findHistoriesByTicketId(id);
    }

    public Long createTicketHistory(Ticket ticket, User user){
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription("Ticket is created");
        history.setAction("Ticket is created");
        return historyRepository.saveHistory(history);
    }
    public Long editTicketHistory(Ticket ticket,User user){
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription("Ticket is edited");
        history.setAction("Ticket is edited");
        return historyRepository.saveHistory(history);
    }
    public Long statusChangedTicketHistory(Ticket ticket,User user,State oldState){
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setTicket(ticket);
        history.setUser(user);
        history.setDescription(String.format("Ticket istatus is changed from '%s' to '%s'",
                oldState,ticket.getState().name()));
        history.setAction("Ticket istatus is changed");
        return historyRepository.saveHistory(history);
    }
}

