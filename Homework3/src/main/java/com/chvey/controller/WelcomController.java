package com.chvey.controller;


import com.chvey.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class WelcomController {
    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/")
    public String getWelcom() {
        return "/login";
    }

    @RequestMapping("/login")
    public String login(Principal principal, ModelMap model) {
        if (principal != null) {
            model.addAttribute("products", productsRepository.getProducts());
            return "product";
        } else {
            return "login";
        }
    }
}
