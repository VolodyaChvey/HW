package com.chvey.controller;

import com.chvey.converters.HistoryConverter;
import com.chvey.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class HistoryController {
    private HistoryConverter historyConverter;
    private HistoryService historyService;
    @Autowired
    public HistoryController(HistoryConverter historyConverter, HistoryService historyService) {
        this.historyConverter = historyConverter;
        this.historyService = historyService;
    }
    @GetMapping(value = "/{ticketId}/history")
    public ResponseEntity getHistories(@PathVariable Long ticketId){
        return ResponseEntity.ok(historyService.getHistoriesByTicketId(ticketId)
           .stream().map(historyConverter::toDto).collect(Collectors.toList()));
    }
}
