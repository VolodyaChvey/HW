package com.chvey.controller;

import com.chvey.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductsRepository productsRepository;

    @RequestMapping("/product")
    public String registerUser(Principal principal, ModelMap model) {
        List priceList = productsRepository.getAll();
        model.addAttribute("products", priceList);
        model.addAttribute("userName", principal.getName());
        return "product";
    }
}
