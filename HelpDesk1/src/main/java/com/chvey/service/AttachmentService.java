package com.chvey.service;

import com.chvey.domain.Attachment;
import com.chvey.domain.Ticket;
import com.chvey.exceptions.AttachmentException;
import com.chvey.exceptions.TicketNotFoundException;
import com.chvey.repository.AttachmentRepository;
import com.chvey.validators.AttachmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private AttachmentValidator attachmentValidator;

    public boolean save(CommonsMultipartFile[] files, Ticket ticket) {
        try {
            if (attachmentValidator.save(files)) {
                for (CommonsMultipartFile aFile : files) {
                    Attachment attachment = new Attachment();
                    attachment.setName(aFile.getOriginalFilename());
                    attachment.setBlob(aFile.getBytes());
                    attachment.setTicket(ticket);
                    if (!nonNull(attachmentRepository.save(attachment))) {
                        throw new AttachmentException("Failed to save attachment");
                    }
                    ;
                    historyService.addAttachment(attachment);
                }
            }
            return true;
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean saveByTicketId(CommonsMultipartFile[] files, Long ticketId) {
        try {
            Ticket ticket = ticketService.getTicketById(ticketId)
                    .orElseThrow(() -> new TicketNotFoundException("Ticket is not found"));
            if (attachmentValidator.save(files)) {
                for (CommonsMultipartFile aFile : files) {
                    Attachment attachment = new Attachment();
                    attachment.setName(aFile.getOriginalFilename());
                    attachment.setBlob(aFile.getBytes());
                    attachment.setTicket(ticket);
                    if (!nonNull(attachmentRepository.save(attachment))) {
                        throw new AttachmentException("Failed to save attachment");
                    };
                    historyService.addAttachment(attachment);
                }
            }
            return true;
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Optional<Attachment> getAttachmentById(Long id) {
        return Optional.ofNullable(attachmentRepository.findAttachmentById(id));
    }

    public Optional<List<Attachment>> getAttachmentsByTicketId(Long id) {
        return Optional.ofNullable(attachmentRepository.findAttachmentsByTicketId(id));

    }

    public boolean removeAttachmentById(Long id) {
        try {
            Attachment attachment = attachmentRepository.findAttachmentById(id);
            if (nonNull(attachment)) {
                if (nonNull(attachmentRepository.remove(attachment))) {
                    historyService.removeAttachment(attachment);
                    return true;
                }
            }
            throw new AttachmentException("Failed to delete attachment " + id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
