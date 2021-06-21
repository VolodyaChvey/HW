package com.chvey.controller;

import com.chvey.Config.SpringContex;
import com.chvey.domain.Order;
import com.chvey.domain.User;
import com.chvey.repository.OrdersRepository;
import com.chvey.service.OrderService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.*;

@WebServlet(name = "OrderServlet", urlPatterns = {"/servlet3"})
public class OrderServlet extends HttpServlet {
    ApplicationContext context = SpringContex.getApplicationContext();
    private OrderService orderService;
    private OrdersRepository ordersRepository;

    @Override
    public void init() throws ServletException {
        orderService = context.getBean("orderService", OrderService.class);
        ordersRepository = context.getBean("ordersRepository", OrdersRepository.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userName");
        List<String> products = Arrays.asList(req.getParameter("products").split(","));
        Order order = orderService.createOrder(user, products);
        List priceOrder = ordersRepository.getOrderGood(order.getId());
        req.setAttribute("priceOrder", priceOrder);
        req.setAttribute("userName", user);
        String totalPrice = String.format("%.2f", order.getTotalPrice());
        req.setAttribute("totalPrice", totalPrice);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/order.jsp");
        dispatcher.forward(req, resp);
    }
}
