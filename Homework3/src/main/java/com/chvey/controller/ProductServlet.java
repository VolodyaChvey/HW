package com.chvey.controller;

import com.chvey.Config.SpringContex;
import com.chvey.domain.User;
import com.chvey.repository.ProductsRepository;
import com.chvey.service.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.Map;

@WebServlet(name = "ProductServlet", urlPatterns = {"/servlet2"})
public class ProductServlet extends HttpServlet {
    ApplicationContext context = SpringContex.getApplicationContext();
    private ProductsRepository productsRepository;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        productsRepository = context.getBean("productsRepository", ProductsRepository.class);
        userService = context.getBean("userService", UserService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userName = req.getParameter("userName");
        String checkbox = req.getParameter("checkbox");
        User user = userService.createOrGet(userName);
        session.setAttribute("userName", user);
        session.setAttribute("checkbox", checkbox);
        Map<String, Double> priceList = productsRepository.getProducts();
        req.setAttribute("products", priceList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/product.jsp");
        dispatcher.forward(req, resp);
    }
}
