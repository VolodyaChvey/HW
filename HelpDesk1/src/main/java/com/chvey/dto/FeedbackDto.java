package com.chvey.dto;

public class FeedbackDto {
    private Long id;
    private byte rate;
    private String date;
    private String text;
    private UserDto userDto;
    private Long ticketId;

    public FeedbackDto() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "FeedbackDto{" +
                "id=" + id +
                ", rate=" + rate +
                ", date='" + date + '\'' +
                ", text='" + text + '\'' +
                ", userDto=" + userDto +
                ", ticketId=" + ticketId +
                '}';
    }
}
