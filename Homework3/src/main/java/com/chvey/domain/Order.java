package com.chvey.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(optional = false,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @ManyToMany
    @JoinTable(name = "Order_Good",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "good_id"))
   private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Order() {
    }

    public Order(User user, double totalPrice) {

        this.user = user;
        this.totalPrice = totalPrice;
    }

    public void setUser_id(User user) {
        this.user = user;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}


