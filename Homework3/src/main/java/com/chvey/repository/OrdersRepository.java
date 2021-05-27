package com.chvey.repository;

import com.chvey.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class OrdersRepository {

    Map<Long, Order> database = new HashMap<>();

    public Map<Long, Order> getDatabase() {
        return database;
    }

    public void setDatabase(Map<Long, Order> database) {
        this.database = database;
    }
}
