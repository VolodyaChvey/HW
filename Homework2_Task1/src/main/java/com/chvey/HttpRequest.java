package com.chvey;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    String parentUrl = "https://jsonplaceholder.typicode.com/posts/";
    ObjectMapper mapper = new ObjectMapper();

    void get(String id) {
        String urlGet = parentUrl + id;
        try {
            URL url = new URL(urlGet);
            System.out.println(mapper.readValue(url, Article.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void post(String id) {
        try {
            URL url = new URL(parentUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            Article article = new Article(Integer.parseInt(id), 1, "some title", "somemessage");
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(article.forPOSTRequest());
            out.flush();
            out.close();
            System.out.println(mapper.readValue(connection.getInputStream(), Article.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
