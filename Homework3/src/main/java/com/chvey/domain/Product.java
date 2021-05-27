package com.chvey.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private static AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String name;
    private double price;
    public Product(String name){
        this.name=name;
        this.id=count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
