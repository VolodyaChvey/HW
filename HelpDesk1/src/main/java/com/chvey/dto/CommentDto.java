package com.chvey.dto;

import java.util.Objects;

public class CommentDto {
    private Long id;
    private String date;
    private String text;
    private Long ticketId;
    private UserDto userDto;

    public CommentDto() {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        return "CommentDto{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", text='" + text + '\'' +
                ", ticketId=" + ticketId +
                ", userDto=" + userDto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return id.equals(that.id) &&
                date.equals(that.date) &&
                text.equals(that.text) &&
                ticketId.equals(that.ticketId) &&
                userDto.equals(that.userDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, text, ticketId, userDto);
    }
}
