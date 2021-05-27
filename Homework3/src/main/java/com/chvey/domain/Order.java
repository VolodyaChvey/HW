package com.chvey.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static AtomicInteger count = new AtomicInteger(0);
    private int id;
    private User customer;
    private List<Product> products = new ArrayList<>();
    private double totalPrice;

    public Order(User customer) {
        this.customer = customer;
        this.id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}


