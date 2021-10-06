package com.chvey.validators;

import com.chvey.domain.Ticket;
import com.chvey.domain.User;
import com.chvey.domain.enums.Role;
import com.chvey.domain.enums.State;
import com.chvey.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.util.Objects.nonNull;

@Component
public class TicketValidator {
    @Autowired
    private TextValidator textValidator;

    public boolean dto(TicketDto ticketDto) {
        return nonNull(ticketDto.getCategory())
                && (nonNull(ticketDto.getName()) && textValidator.name(ticketDto.getName()))
                && (!nonNull(ticketDto.getDescription()) || textValidator.text(ticketDto.getDescription()))
                && nonNull(ticketDto.getUrgency())
                && (!nonNull(ticketDto.getDesiredResolutionDate())
                || LocalDate.parse(ticketDto.getDesiredResolutionDate()).compareTo(LocalDate.now()) >= 0);
    }

    public boolean checkStateNew(User user, Ticket ticket, State stateNew) {
        boolean result = false;
        switch (stateNew) {
            case NEW:
                if (ticket.getState() == State.DRAFT || ticket.getState() == State.DECLINED) {
                    if ((user.getRole() == Role.EMPLOYEE || user.getRole() == Role.MANAGER)
                            && ticket.getOwner().getId() == user.getId()) {
                        result = true;
                    }
                }
                break;
            case APPROVED:
                if (ticket.getState() == State.NEW) {
                    if (user.getRole() == Role.MANAGER && ticket.getOwner().getId() != user.getId()) {
                        result = true;
                    }
                }
                break;
            case DECLINED:
                if (ticket.getState() == State.NEW) {
                    if (user.getRole() == Role.MANAGER && ticket.getOwner().getId() != user.getId()) {
                        result = true;
                    }
                }
                break;
            case CANCELED:
                if (ticket.getState() == State.DRAFT || ticket.getState() == State.DECLINED) {
                    if ((user.getRole() == Role.EMPLOYEE || user.getRole() == Role.MANAGER)
                            && ticket.getOwner().getId() == user.getId()) {
                        result = true;
                    }
                }
                if (ticket.getState() == State.NEW) {
                    if (user.getRole() == Role.MANAGER && ticket.getOwner().getId() != user.getId()) {
                        result = true;
                    }
                }
                if (ticket.getState() == State.APPROVED) {
                    if (user.getRole() == Role.ENGINEER) {
                        result = true;
                    }
                }
            case IN_PROGRESS:
                if (ticket.getState() == State.APPROVED) {
                    if (user.getRole() == Role.ENGINEER) {
                        result = true;
                    }
                }
            case DONE:
                if (ticket.getState() == State.IN_PROGRESS) {
                    if (user.getRole() == Role.ENGINEER) {
                        result = true;
                    }
                }
        }
        return result;
    }
}


