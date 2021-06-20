package com.chvey.domain;

public class Order {

    private long id;
    private int user_id;
    private double totalPrice;

    public Order( int user_id, double totalPrice) {

        this.user_id = user_id;
        this.totalPrice = totalPrice;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public long getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}


