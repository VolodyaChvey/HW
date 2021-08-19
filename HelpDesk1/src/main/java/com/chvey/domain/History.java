package com.chvey.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "TICKET_ID", nullable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public History() {
    }

    public History(Long id, LocalDateTime date, String action, String description, Ticket ticket, User user) {
        this.id = id;
        this.date = date;
        this.action = action;
        this.description = description;
        this.ticket = ticket;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", date=" + date +
                ", action='" + action + '\'' +
                ", description='" + description + '\'' +
                ", ticket=" + ticket +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return id.equals(history.id) &&
                date.equals(history.date) &&
                action.equals(history.action) &&
                description.equals(history.description) &&
                ticket.equals(history.ticket) &&
                user.equals(history.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, action, description, ticket, user);
    }
}
