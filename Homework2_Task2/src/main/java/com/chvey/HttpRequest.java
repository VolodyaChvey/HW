package com.chvey;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpRequest {
    private String url = "https://jsonplaceholder.typicode.com/posts/";
    private ObjectMapper mapper = new ObjectMapper();
    private CloseableHttpClient httpclient = HttpClients.createDefault();

    void get(String id) {
        HttpUriRequest httpCet = new HttpGet(url + id);
        try {
            CloseableHttpResponse response1 = httpclient.execute(httpCet);
            HttpEntity entity1 = response1.getEntity();
            System.out.println(mapper.readValue(EntityUtils.toString(entity1), Article.class));
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void post(String id) {
        HttpPost httpPost = new HttpPost(url);
        Article article = new Article(Integer.parseInt(id), 1, "some title", "somemessage");
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(article.toPOSTRequest()));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            System.out.println(mapper.readValue(EntityUtils.toString(entity2), Article.class));
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
