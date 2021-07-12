package com.chvey.service;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.domain.User;
import com.chvey.repository.OrdersRepository;
import com.chvey.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    public Order createOrder(User user, List<String> products) {
        double totalPrice = products.stream()
                .map(p -> productsRepository.getProd(p))
                .mapToDouble(Product::getPrice)
                .sum();
        Order order = ordersRepository.save(user, totalPrice);
        ordersRepository.setOrderGood(order.getId(), products);
        return order;
    }
}
