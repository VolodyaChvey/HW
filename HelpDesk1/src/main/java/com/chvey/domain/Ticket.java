package com.chvey.domain;

import com.chvey.domain.enums.State;
import com.chvey.domain.enums.Urgency;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TICKET")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "CREATED_ON",nullable = false)
    private LocalDate createdOn;

    @Column(name = "DESIRED_RESOLUTION_DATE")
    private LocalDate desiredResolutionDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, name="STATE_ID")
    private State state;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, name = "URGENCY_ID")
    private Urgency urgency;

    @ManyToOne
    @JoinColumn(name = "ASSIGNEE_ID")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "APPROVER_ID")
    private User approver;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Transient
    private List<Comment> comments;

    @Transient
    private List<History> histories;

    @Transient
    private List<Attachment> attachments;
    public Ticket() {
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getDesiredResolutionDate() {
        return desiredResolutionDate;
    }

    public void setDesiredResolutionDate(LocalDate desiredResolutionDate) {
        this.desiredResolutionDate = desiredResolutionDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id.equals(ticket.id) &&
                name.equals(ticket.name) &&
                description.equals(ticket.description) &&
                createdOn.equals(ticket.createdOn) &&
                desiredResolutionDate.equals(ticket.desiredResolutionDate) &&
                state == ticket.state &&
                urgency == ticket.urgency &&
                category.equals(ticket.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdOn, desiredResolutionDate, state, urgency, category);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", desiredResolutionDate=" + desiredResolutionDate +
                ", state=" + state +
                ", urgency=" + urgency +
                ", category=" + category +
                '}';
    }
}
