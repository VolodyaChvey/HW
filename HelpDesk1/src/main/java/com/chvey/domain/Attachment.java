package com.chvey.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1024*5*1024, nullable = false)
    private byte[] blob;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "TICKET_ID", nullable = false)
    private Ticket ticket;

    public Attachment() {
    }

    public Attachment(Long id, byte[] blob, String name, Ticket ticket) {
        this.id = id;
        this.blob = blob;
        this.name = name;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", blob=" + Arrays.toString(blob) +
                ", name='" + name + '\'' +
                ", ticket=" + ticket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return id.equals(that.id) &&
                Arrays.equals(blob, that.blob) &&
                name.equals(that.name) &&
                ticket.equals(that.ticket);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, ticket);
        result = 31 * result + Arrays.hashCode(blob);
        return result;
    }
}
