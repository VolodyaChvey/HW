package com.chvey.cotroller;

import com.chvey.converters.TicketConverter;
import com.chvey.dto.TicketDto;
import com.chvey.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

    @GetMapping(value = "/my")
    public ResponseEntity getTicketsMy(Principal principal) {
        return ResponseEntity.ok(ticketService.getMyTickets(principal.getName())
                .stream().map(ticketConverter::toDto).toArray());
    }


    @PostMapping()
    public ResponseEntity toSaveTicket(Principal principal, @RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketConverter.toDto(ticketService.getSaveTicket(principal.getName(), ticketConverter.toEntity(ticketDto))));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketConverter.toDto(ticketService.getTicketById(id)));
    }

    @PutMapping(value = "/{id}")
    public void updateTicket(Principal principal, @RequestBody TicketDto ticketDto) {
        ticketService.editTicket(principal.getName(), ticketConverter.toEntity(ticketDto));
    }

    @PutMapping(value = "/{id}/{status}")
    public void changeTicket(Principal principal, @PathVariable Long id, @PathVariable String status) {
        ticketService.changeTicketState(id, status, principal.getName());
    }

    @PostMapping(value = "/draft")
    public ResponseEntity createDraft(@RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
                                      @RequestParam(value = "ticketDto") TicketDto ticketDto, Principal principal) {
        System.out.println(files + ticketDto.toString() + principal.getName());
   // ticketService.createTicket(ticketConverter.fromJson(ticketDto), files, principal.getName(), State.DRAFT);
        return new ResponseEntity(HttpStatus.OK);
    }
}
