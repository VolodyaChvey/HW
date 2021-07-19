package com.chvey.controller;

import com.chvey.DTO.ProductsDTO;
import com.chvey.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class ProductController {
    @Autowired
    private Converter converter;

    @RequestMapping("/products")
   /* public String registerUser(Principal principal, ModelMap model) {

        ProductsDTO productsDTO = converter.convertProductsDTO(principal.getName());
        model.addAttribute("productsDTO", productsDTO);
        return "product";
    }*/
    public ModelAndView registerUser(Principal principal){
        ProductsDTO productsDTO = converter.convertProductsDTO(principal.getName());
        return new ModelAndView("product","productsDTO",productsDTO);
    }
  /*  @GetMapping("/products")
   public ProductsDTO showProducts(Principal principal) {
        return converter.convertProductsDTO(principal.getName());
    }*/
}
