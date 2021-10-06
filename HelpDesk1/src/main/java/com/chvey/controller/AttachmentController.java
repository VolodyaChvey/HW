package com.chvey.controller;

import com.chvey.converters.AttachmentConverter;
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
    private AttachmentService attachmentService;
    private AttachmentConverter attachmentConverter;

    @Autowired
    public AttachmentController(AttachmentService attachmentService, AttachmentConverter attachmentConverter) {
        this.attachmentService = attachmentService;
        this.attachmentConverter = attachmentConverter;
    }

    @PostMapping(value = "tickets/{id}/attachments")
    public ResponseEntity addAttachmentsByTicketId(@RequestParam(name = "files") CommonsMultipartFile[] files,
                                                   @PathVariable Long id) {
        return attachmentService.saveByTicketId(files, id)
                ? new ResponseEntity(HttpStatus.CREATED)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "attachments/{id}")
    public ResponseEntity getAttachment(@PathVariable Long id) {
        return attachmentService.getAttachmentById(id)
                .map(attachment -> ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment: filename-\"" + attachment.getName() + "\"").body(attachment.getBlob()))
                .orElseGet(() -> new ResponseEntity((HttpStatus.NOT_FOUND)));
    }

    @GetMapping(value = "tickets/{id}/attachments")
    public ResponseEntity getAttachmentsByTicketId(@PathVariable Long id, Principal principal) {
        return attachmentService.getAttachmentsByTicketId(id)
                .map(value -> ResponseEntity.ok(value.stream()
                        .map(attachmentConverter::toDto).collect(Collectors.toList())))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "attachments/{id}")
    public ResponseEntity deleteAttachment(@PathVariable Long id) {
        return attachmentService.removeAttachmentById(id)
                ? new ResponseEntity(HttpStatus.NO_CONTENT)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
