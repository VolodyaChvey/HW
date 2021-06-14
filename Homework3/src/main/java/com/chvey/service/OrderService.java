package com.chvey.service;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.domain.User;
import com.chvey.repository.OrdersRepository;
import com.chvey.repository.ProductsRepository;

import java.util.List;

public class OrderService {
    private ProductsRepository productsRepository = new ProductsRepository();
    private OrdersRepository ordersRepository = new OrdersRepository();

    public Order createOrder(User user, List<String> products) {
        double totalPrice = products.stream()
                .map(p -> productsRepository.getProd(p))
                .mapToDouble(Product::getPrice)
                .sum();
        Order order = ordersRepository.save(user.getId(), totalPrice);
        ordersRepository.setOrderGood(order.getId(), products);
        return order;
    }
}
