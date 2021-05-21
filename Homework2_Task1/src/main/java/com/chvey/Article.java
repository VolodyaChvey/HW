package com.chvey;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Article {
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;

    public Article() {
    }

    public Article(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String forPOSTRequest() {
        StringBuilder result = new StringBuilder();
        try {
            result.append(URLEncoder.encode("userId", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(String.valueOf(userId), "UTF-8"));
            result.append("&");
            result.append(URLEncoder.encode("id", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(String.valueOf(id), "UTF-8"));
            result.append("&");
            result.append(URLEncoder.encode("title", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(title, "UTF-8"));
            result.append("&");
            result.append(URLEncoder.encode("body", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(body, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return "Article [" + userId +
                "]: User [" + id +
                "] Title [" + '\"' + title + '\"' +
                "] Message [" + '\"' + body + '\"' +
                ']';
    }
}
