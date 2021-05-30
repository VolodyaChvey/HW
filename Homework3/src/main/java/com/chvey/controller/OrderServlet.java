package com.chvey.controller;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.domain.User;
import com.chvey.service.OrderService;
import com.chvey.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class OrderServlet extends HttpServlet {
    private UserService userService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        orderService = new OrderService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> products = Arrays.asList(req.getParameterValues("products"));
        User user = userService.createOrGet(req.getParameter("userName"));
        Order order = orderService.createOrder(user, products);
        PrintWriter pw = resp.getWriter();
        pw.println("<body>\n" +
                "    <style>\n" +
                "        .flex-container {\n" +
                "            display: flex;\n" +
                "            justify-content: space-around;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <h2 align=\"center\">Dear " + order.getCustomer().getName() + ", your order</h2>\n" +
                "    <div class=\"flex-container\">\n" +
                "        <div>\n" +
                "            <ol>\n");
        for (Product p : order.getProducts()) {
            pw.printf("<li>  %s %s $</li>", p.getName(), p.getPrice());
        }
        pw.println("            </ol>\n" +
                "            <p>Total: $ " + order.getTotalPrice() + "</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>");
        pw.close();
    }
}
