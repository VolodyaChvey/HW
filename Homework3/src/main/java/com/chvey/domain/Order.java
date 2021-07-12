package com.chvey.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Order")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    @ManyToMany
    @JoinTable(name = "Order_Good",
            joinColumns = @JoinColumn(name = "orser_id"),
            inverseJoinColumns = @JoinColumn(name = "good_id"))
    private long id;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private int user_id;
    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    public Order() {
    }

    public Order(int user_id, double totalPrice) {

        this.user_id = user_id;
        this.totalPrice = totalPrice;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setTotalPrice(double totalPrice) {
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


