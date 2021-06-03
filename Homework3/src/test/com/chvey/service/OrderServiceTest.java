package com.chvey.service;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.domain.User;
import com.chvey.repository.ProductsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderServiceTest {

    OrderService orderService;

    @Before
    public void setUp() throws Exception {
        orderService = new OrderService();
    }

    @Test
    private void addProductToOrder() {

    }


}