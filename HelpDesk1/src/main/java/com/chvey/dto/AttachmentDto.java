package com.chvey.dto;

import java.util.Arrays;

public class AttachmentDto {
    private Long id;
    private String name;
    private byte[] blob;
    private Long ticketId;

    public AttachmentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "AttachmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blob=" + Arrays.toString(blob) +
                ", ticketId=" + ticketId +
                '}';
    }
}
