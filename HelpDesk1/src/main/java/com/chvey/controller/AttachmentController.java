package com.chvey.controller;

import com.chvey.converters.AttachmentConverter;
import com.chvey.domain.Attachment;
import com.chvey.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private AttachmentConverter attachmentConverter;

    @PostMapping(value = "tickets/{id}/attachments")
    public ResponseEntity addAttachmentsByTicketId(@RequestParam(name = "files") CommonsMultipartFile[] files,
                                                   @PathVariable Long id, Principal principal) {
        attachmentService.saveByTicketId(files, id);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping(value= "attachments/{id}")
    public ResponseEntity getAttachment(@PathVariable Long id){
        Attachment attachment= attachmentService.getAttachmentById(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment: filename-\""+attachment.getName()+"\"").body(attachment.getBlob());
    }
    @GetMapping(value = "tickets/{id}/attachments")
    public ResponseEntity getAttachmentsByTicketId(@PathVariable Long id,Principal principal){
        return ResponseEntity.ok(attachmentService.getAttachmentsByTicketId(id).stream()
            .map(attachmentConverter::toDto).collect(Collectors.toList()));
    }
    @DeleteMapping(value = "attachments/{id}")
    public ResponseEntity deleteAttachment(@PathVariable Long id, Principal principal){
        attachmentService.removeAttachmentById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
