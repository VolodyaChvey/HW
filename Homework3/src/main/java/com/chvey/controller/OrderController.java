package com.chvey.controller;

import com.chvey.domain.Order;
import com.chvey.domain.User;
import com.chvey.repository.OrdersRepository;
import com.chvey.service.OrderService;
import com.chvey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller

public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private UserService userService;

    @RequestMapping("/order")
    public String showOrder(Principal principal, ModelMap model, @RequestParam String products) {
        User user = userService.createOrGet(principal.getName());
        List<String> product = Arrays.asList(products.split(","));
        Order order = orderService.createOrder(user, product);
        List priceOrder = ordersRepository.getOrderGood(order.getId());
        String totalPrice = String.format("%.2f", order.getTotalPrice());
        model.addAttribute("priceOrder", priceOrder);
        model.addAttribute("userName", principal.getName());
        model.addAttribute("totalPrice", totalPrice);

        return "order";
    }
}
