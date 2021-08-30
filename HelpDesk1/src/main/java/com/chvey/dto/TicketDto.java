package com.chvey.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("createdOn")
    private String createdOn;
    @JsonProperty("desiredResolutionDate")
    private String desiredResolutionDate;
    @JsonProperty("state")
    private String state;
    @JsonProperty("urgency")
    private String urgency;
    @JsonProperty("assignee")
    private UserDto assignee;
    @JsonProperty("owner")
    private UserDto owner;
    @JsonProperty("approver")
    private UserDto approver;
    @JsonProperty("category")
    private Long category;

    public TicketDto() {
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

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getDesiredResolutionDate() {
        return desiredResolutionDate;
    }

    public void setDesiredResolutionDate(String desiredResolutionDate) {
        this.desiredResolutionDate = desiredResolutionDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public UserDto getAssignee() {
        return assignee;
    }

    public void setAssignee(UserDto assignee) {
        this.assignee = assignee;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public UserDto getApprover() {
        return approver;
    }

    public void setApprover(UserDto approver) {
        this.approver = approver;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", desiredResolutionDate=" + desiredResolutionDate +
                ", state=" + state +
                ", urgency=" + urgency +
                ", assignee=" + assignee +
                ", owner=" + owner +
                ", approver=" + approver +
                ", category=" + category +
                '}';
    }
}
