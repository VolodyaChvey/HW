package com.chvey.controller;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.domain.User;
import com.chvey.repository.ProductsRepository;
import com.chvey.service.OrderService;
import com.chvey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductsRepository productsRepository;

    @RequestMapping("/order")
    public String showOrder(Principal principal, ModelMap model, @RequestParam String products) {
        User user = userService.createOrGet(principal.getName());
        List<String> product = Arrays.asList(products.split(","));
        List<Product> listProducts = product.stream()
                .map(p -> productsRepository.getProd(p))
                .collect(Collectors.toList());
        Order order = orderService.createOrder(user, listProducts);
        String totalPrice = String.format("%.2f", order.getTotalPrice());
        model.addAttribute("priceOrder", order.getProducts());
        model.addAttribute("userName", user.getName());
        model.addAttribute("totalPrice", totalPrice);
        return "order";
    }
}
