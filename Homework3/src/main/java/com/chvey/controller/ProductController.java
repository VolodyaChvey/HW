package com.chvey.controller;

import com.chvey.domain.Product;
import com.chvey.domain.User;
import com.chvey.repository.ProductsRepository;
import com.chvey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller

public class ProductController {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private UserService userService;

    @RequestMapping("/product")
    public String registerUser(Principal principal, ModelMap model) {
        User user = userService.createOrGet(principal.getName());
        List<Product> priceList = productsRepository.getAll();

        model.addAttribute("products", priceList);
        model.addAttribute("userName", principal.getName());

        return "product";
    }
}
