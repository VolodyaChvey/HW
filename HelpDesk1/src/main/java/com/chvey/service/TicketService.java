package com.chvey.service;

import com.chvey.domain.Ticket;
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

    public List<Ticket> getTicketsByUserId (int userId){
        return ticketRepository.findTicketsByUserId(userId);
    }
}
