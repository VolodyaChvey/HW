package com.chvey.service;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.domain.User;
import com.chvey.repository.OrdersRepository;
import com.chvey.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private UserService userService;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductsRepository productsRepository;

    public Order createOrder(String userName, String products) {
        User user = userService.createOrGet(userName);
        List<Product> listProducts = mappingStringToProduct(products);
        double totalPrice = sumPrice(listProducts);
        Order order = new Order(user, totalPrice, listProducts);
        order.setId(ordersRepository.save(new Order(user, totalPrice, listProducts)));
        return order;
    }

    private List<Product> mappingStringToProduct(String products) {
        List<String> productsListString = Arrays.asList(products.split(","));
        return productsListString.stream()
                .map(p -> productsRepository.getProd(p))
                .collect(Collectors.toList());
    }

    private double sumPrice(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
