package com.chvey.repository;


import java.util.HashMap;
import java.util.Map;

public class ProductsRepository {
    private static final Map<String, Double> priceList = new HashMap<>();
    static {
        priceList.put("product1",3.5);
        priceList.put("product2",12.3);
        priceList.put("product3",10.6);
        priceList.put("product4",3.7);
        priceList.put("product5",5.2);
    }


    public static Map getProducts(){
        return priceList;
    }

}
