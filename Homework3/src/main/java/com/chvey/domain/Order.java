package com.chvey.domain;

public class Order {
    private int id;
    private int User_id;
    private double totalPrice;
    private String date;

    public Order(int id, int user_id, double totalPrice, String date) {
        this.id = id;
        User_id = user_id;
        this.totalPrice = totalPrice;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}


