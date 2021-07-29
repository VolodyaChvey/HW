package com.chvey.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String blob;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "TICKET_ID", nullable = false)
    private Ticket ticket;

    public Attachment() {
    }

    public Attachment(Long id, String blob, String name, Ticket ticket) {
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

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return id.equals(that.id) &&
                blob.equals(that.blob) &&
                name.equals(that.name) &&
                ticket.equals(that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blob, name, ticket);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", blob='" + blob + '\'' +
                ", name='" + name + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
