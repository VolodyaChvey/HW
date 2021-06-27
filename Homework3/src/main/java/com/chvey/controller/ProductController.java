package com.chvey.controller;

import com.chvey.domain.User;
import com.chvey.repository.ProductsRepository;
import com.chvey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private UserService userService;


    public String registerUser(@RequestParam String userName, @RequestParam Boolean checkbox, HttpSession session) {
        User user = userService.createOrGet(userName);
        Map<String, Double> priceList = productsRepository.getProducts();
        session.setAttribute("userName", user);
        session.setAttribute("checkbox", checkbox);
        session.setAttribute("products", priceList);


        return "product";
    }
}
