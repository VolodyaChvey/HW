package com.chvey.converters;

import com.chvey.domain.Attachment;
import com.chvey.dto.AttachmentDto;
import com.chvey.exceptions.TicketNotFoundException;
import com.chvey.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttachmentConverter {
    @Autowired
    private TicketService ticketService;

    public AttachmentDto toDto(Attachment attachment) {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setId(attachment.getId());
        attachmentDto.setBlob(attachment.getBlob());
        attachmentDto.setName(attachment.getName());
        attachmentDto.setTicketId(attachment.getTicket().getId());
        return attachmentDto;
    }

    public Attachment toEntity(AttachmentDto attachmentDto) throws TicketNotFoundException{
        Attachment attachment = new Attachment();
        attachment.setId(attachmentDto.getId());
        attachment.setBlob(attachmentDto.getBlob());
        attachment.setName(attachmentDto.getName());
        attachment.setTicket(ticketService.getTicketById(attachmentDto.getTicketId())
                .orElseThrow(()->new TicketNotFoundException("Ticket is not found")));
        return attachment;
    }
}
