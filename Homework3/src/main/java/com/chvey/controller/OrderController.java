package com.chvey.controller;

import com.chvey.domain.Order;
import com.chvey.domain.User;
import com.chvey.repository.OrdersRepository;
import com.chvey.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrdersRepository ordersRepository;

    public String showOrder(HttpSession session, @RequestParam String products) {
        User user = (User) session.getAttribute("userName");
        List<String> product = Arrays.asList(products.split(","));
        Order order = orderService.createOrder(user, product);
        List priceOrder = ordersRepository.getOrderGood(order.getId());
        String totalPrice = String.format("%.2f", order.getTotalPrice());
        session.setAttribute("priceOrder", priceOrder);
        session.setAttribute("userName", user);
        session.setAttribute("totalPrice", totalPrice);

        return "order";
    }
}
