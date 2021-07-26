package com.chvey.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private byte rate;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "TICKET_ID",nullable = false)
    private Ticket ticket;

    public Feedback() {
    }

    public Feedback(Long id, byte rate, LocalDate date, String text, User user, Ticket ticket) {
        this.id = id;
        this.rate = rate;
        this.date = date;
        this.text = text;
        this.user = user;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte getRate() {
        return rate;
    }

    public void setRate(byte rate) {
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Feedback feedback = (Feedback) o;
        return rate == feedback.rate &&
                id.equals(feedback.id) &&
                date.equals(feedback.date) &&
                text.equals(feedback.text) &&
                user.equals(feedback.user) &&
                ticket.equals(feedback.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rate, date, text, user, ticket);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", rate=" + rate +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", ticket=" + ticket +
                '}';
    }
}
