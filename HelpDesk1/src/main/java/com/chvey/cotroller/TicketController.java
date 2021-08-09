package com.chvey.cotroller;

import com.chvey.converters.TicketConverter;
import com.chvey.domain.User;
import com.chvey.service.TicketService;
import com.chvey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        User user = userService.getUserByEmail(principal.getName());

        return ResponseEntity.ok(ticketService.getTicketsByUserId(user)
                .stream().map(ticketConverter::toDto).toArray());
    }
}
