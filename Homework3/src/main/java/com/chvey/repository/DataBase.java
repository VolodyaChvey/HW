package com.chvey.repository;

import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private String userName;
    private Map<String, Float> products = new HashMap<>();

    public DataBase(String userName) {
        this.userName = userName;
        this.products.put("book", 5.5f);
        this.products.put("phone", 10f);
        this.products.put("car", 12.2f);
        this.products.put("hause", 21.6f);
        this.products.put("pen", 7.4f);
    }

    public String getUserName() {
        return userName;
    }

    public Map<String, Float> getProducts() {
        return products;
    }
}
