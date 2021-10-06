package com.chvey.dto;

import java.util.List;

public class TicketDto {
    private Long id;
    private String name;
    private String description;
    private String createdOn;
    private String desiredResolutionDate;
    private String state;
    private String urgency;
    private UserDto assignee;
    private UserDto owner;
    private UserDto approver;
    private Long category;
    private String comment;

    private List<CommentDto> comments;
    private List<HistoryDto> histories;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

  /*  public List<AttachmentDto> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDto> attachments) {
        this.attachments = attachments;
    }*/

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public List<HistoryDto> getHistories() {
        return histories;
    }

    public void setHistories(List<HistoryDto> histories) {
        this.histories = histories;
    }


}
