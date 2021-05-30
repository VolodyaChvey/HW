package com.chvey.repository;

import com.chvey.domain.Order;

import java.util.*;

public class OrdersRepository {

    private List<Order> orders=new ArrayList<>();



    public Order save (Order order){
        orders.add(order);
        return order;
    }
    public Optional<Order> getOrderById(Long id){
        return orders.stream()
                .filter(o -> o.getCustomer().getId()==id)
                .findFirst();
    }
}
