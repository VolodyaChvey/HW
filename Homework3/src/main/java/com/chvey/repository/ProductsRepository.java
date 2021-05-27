package com.chvey.repository;

import com.chvey.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductsRepository {
    private Map<Long, Product> productDB = new HashMap<>();

    public Map<Long, Product> getProductDB() {
        return productDB;
    }

    public void setProductDB(Map<Long, Product> productDB) {
        this.productDB = productDB;
    }
}
