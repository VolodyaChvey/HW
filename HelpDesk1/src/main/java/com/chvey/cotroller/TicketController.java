package com.chvey.cotroller;

import com.chvey.converters.TicketConverter;
import com.chvey.dto.TicketDto;
import com.chvey.service.TicketService;
import com.chvey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private UserService userService;
    private TicketService ticketService;
    private TicketConverter ticketConverter;

    @Autowired
    public TicketController(UserService userService, TicketService ticketService, TicketConverter ticketConverter) {
        this.userService = userService;
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
        TicketDto cl=ticketConverter.toDto(ticketService.getSaveTicket(principal.getName(), ticketConverter.toEntity(ticketDto)));
        System.out.println(cl);
        return ResponseEntity.ok(cl);
    }
}
