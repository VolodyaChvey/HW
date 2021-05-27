package com.chvey.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String name;

    public User(String name) {
        this.name = name;
        this.id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
