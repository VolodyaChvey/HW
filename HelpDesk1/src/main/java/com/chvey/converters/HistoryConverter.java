package com.chvey.converters;

import com.chvey.domain.History;
import com.chvey.dto.HistoryDto;
import com.chvey.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HistoryConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private TicketService ticketService;

    public HistoryDto toDto (History history){
        HistoryDto historyDto = new HistoryDto();
        historyDto.setId(history.getId());
        historyDto.setAction(history.getAction());
        historyDto.setDescription(history.getDescription());
        historyDto.setTicketId(history.getTicket().getId());
        historyDto.setUserDto(userConverter.toDto(history.getUser()));
        historyDto.setDate(history.getDate().toString());
        return historyDto;
    }

    public History toEntity (HistoryDto historyDto){
        History history = new History();
        history.setId(history.getId());
        history.setAction(historyDto.getAction());
        history.setDescription(historyDto.getDescription());
        history.setTicket(ticketService.getTicketById(historyDto.getTicketId()));
        history.setUser(userConverter.toEntity(historyDto.getUserDto()));
        history.setDate(LocalDateTime.parse(historyDto.getDate()));
        return history;
    }


}
