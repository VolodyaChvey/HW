package com.chvey;

public class Main {
    public static void main(String[] args) {
        HttpRequest httpRequest = new HttpRequest();
        if (args[0].toLowerCase().equals("get")) {
            httpRequest.get(args[1]);
        }
        if (args[0].toLowerCase().equals("post")) {
            httpRequest.post(args[1]);
        }
    }
}
