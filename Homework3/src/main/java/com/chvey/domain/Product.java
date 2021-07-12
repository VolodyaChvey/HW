package com.chvey.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Good")
public class Product implements Serializable {
    @Id
    @GeneratedValue
    @ManyToMany(mappedBy = "order_id")
    private long id;
    @Column(name = "title", nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}


