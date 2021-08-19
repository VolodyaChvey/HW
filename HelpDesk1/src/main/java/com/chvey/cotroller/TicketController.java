package com.chvey.cotroller;

import com.chvey.converters.TicketConverter;
import com.chvey.dto.TicketDto;
import com.chvey.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping(value = "/all")
    public ResponseEntity getTicketsAll(Principal principal) {
        return ResponseEntity.ok(ticketService.getTicketsByUserId(principal.getName())
                .stream().map(ticketConverter::toDto).toArray());
    }
    @GetMapping(value="/my")
    public ResponseEntity getTicketsMy(Principal principal){
        return ResponseEntity.ok(ticketService.getMyTickets(principal.getName())
                .stream().map(ticketConverter::toDto).toArray());
    }


    @PostMapping()
    public ResponseEntity toSaveTicket(Principal principal, @RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketConverter.toDto(ticketService.getSaveTicket(principal.getName(), ticketConverter.toEntity(ticketDto))));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity getTicketById(@PathVariable Long id){
        return ResponseEntity.ok(ticketConverter.toDto(ticketService.getTicketById(id)));
    }
    @PutMapping(value = "/{id}")
    public void updateTicket(@RequestBody TicketDto ticketDto){
        ticketService.editTicket(ticketConverter.toEntity(ticketDto));
    }
}
