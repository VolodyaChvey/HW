package com.chvey.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static volatile AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.id = count.incrementAndGet();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
