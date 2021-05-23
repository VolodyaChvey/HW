package com.chvey;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

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

    public List<NameValuePair> toPOSTRequest() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", String.valueOf(this.userId)));
        params.add(new BasicNameValuePair("id", String.valueOf(this.id)));
        params.add(new BasicNameValuePair("title", this.title));
        params.add(new BasicNameValuePair("body", this.body));
        return params;
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

