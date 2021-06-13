package com.chvey.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static volatile AtomicInteger count = new AtomicInteger(0);
    private long id;
    private int User_id;
    private double totalPrice;

    public Order(int user_id, double totalPrice) {
        this.id = count.incrementAndGet();
        User_id = user_id;
        this.totalPrice = totalPrice;
    }

    public int getUser_id() {
        return User_id;
    }

    public long getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}


