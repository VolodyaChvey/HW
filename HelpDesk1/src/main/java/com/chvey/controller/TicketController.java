package com.chvey.controller;

import com.chvey.converters.TicketConverter;
import com.chvey.dto.TicketDto;
import com.chvey.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;
    private TicketConverter ticketConverter;

    @Autowired
    public TicketController(TicketService ticketService, TicketConverter ticketConverter) {
        this.ticketService = ticketService;
        this.ticketConverter = ticketConverter;
    }

    @GetMapping(value = "")
    public ResponseEntity getTicketsAll(Principal principal) {
        return ticketService.getTicketsByUserId(principal)
                .map(value -> ResponseEntity.ok(value.stream()
                        .map(ticketConverter::toDto).collect(Collectors.toList())))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity createTicket(@RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
                                       @RequestParam(value = "ticketDto") String ticketDto, Principal principal) {
        return ticketService.createTicket(ticketConverter.fromJson(ticketDto), files, principal)
                .map(ticket -> ResponseEntity.ok(ticketConverter.toDto(ticket)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ticket -> ResponseEntity.ok(ticketConverter.toDto(ticket)))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateTicket(@RequestBody TicketDto ticketDto, Principal principal) {
        return ticketService.editTicket(principal, ticketDto)
                ? new ResponseEntity(HttpStatus.OK)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}/{status}")
    public ResponseEntity changeTicket(Principal principal, @PathVariable Long id, @PathVariable String status) {
        return ticketService.changeTicketState(id, status, principal)
                ? new ResponseEntity(HttpStatus.OK)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
