package com.chvey.service;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.domain.User;
import com.chvey.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    public Order createOrder(User user, List<Product> products) {
        double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        Long orderId = ordersRepository.save(new Order(user, totalPrice, products));
        System.out.println(products);
        return ordersRepository.getOrderById(orderId);
    }
}
