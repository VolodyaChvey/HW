package com.chvey.dto;

public class HistoryDto {
    private Long id;
    private String date;
    private String action;
    private String description;
    private Long ticketId;
    private UserDto userDto;

    public HistoryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "HistoryDto{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", action='" + action + '\'' +
                ", description='" + description + '\'' +
                ", ticketId=" + ticketId +
                ", userDto=" + userDto +
                '}';
    }
}
