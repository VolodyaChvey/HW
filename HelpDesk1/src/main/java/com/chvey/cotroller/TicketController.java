package com.chvey.cotroller;

import com.chvey.converters.TicketConverter;
import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.Role;
import com.chvey.dto.TicketDto;
import com.chvey.service.TicketService;
import com.chvey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketConverter ticketConverter;

    @GetMapping(value = "/")
    public ResponseEntity getTicket(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        if (user.getRole() == Role.EMPLOYEE) {
            return ResponseEntity.ok(ticketService.getTicketsByUserId(user.getId())
                    .stream().map(ticketConverter::toDto));
        } else if (user.getRole() == Role.MANAGER) {
            List<TicketDto> resp = ticketService.getTicketsByUserId(user.getId()).stream().map(ticketConverter::toDto).collect(Collectors.toList());
        }
    }


}
