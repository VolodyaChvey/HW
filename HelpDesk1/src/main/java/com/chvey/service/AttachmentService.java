package com.chvey.service;

import com.chvey.domain.Attachment;
import com.chvey.domain.Ticket;
import com.chvey.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private HistoryService historyService;

    public void save(CommonsMultipartFile[] files, Ticket ticket) {
        for (CommonsMultipartFile aFile : files) {
            if (aFile.getContentType() != null && !aFile.getContentType().equals("image/jpeg") &&
                    !aFile.getContentType().equals("image/png") && !aFile.getContentType().equals("application/pdf") &&
                    !aFile.getContentType().equals("application/msword") && !aFile.getContentType().equals("image/pjpeg") &&
                    !aFile.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                //   throw new IllegalFormatException(String.format("Illigal file format %s", aFile.getContentType()));
            }

        }
        for (CommonsMultipartFile aFile : files) {
            Attachment attachment = new Attachment();
            attachment.setName(aFile.getOriginalFilename());
            attachment.setBlob(aFile.getBytes());
            attachment.setTicket(ticket);
            attachmentRepository.save(attachment);
            historyService.addAttachment(attachment);
        }
    }
}
