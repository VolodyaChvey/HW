package com.chvey.service;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.domain.User;
import com.chvey.repository.OrdersRepository;
import com.chvey.repository.ProductsRepository;

import java.util.List;

public class OrderService {
    private OrdersRepository ordersRepository = new OrdersRepository();

    public Order createOrder(User user, List products) {
        Order order = new Order(user);
        products.stream()
                .filter(p -> ProductsRepository.getProducts().containsKey(p))
                .forEach(p -> order.getProducts().add(new Product((String) p, (Double) ProductsRepository.getProducts().get(p))));
        order.setTotalPrice(order.getProducts().stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum));
        return ordersRepository.save(order);
    }
}
