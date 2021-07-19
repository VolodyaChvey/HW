package com.chvey.controller;

import com.chvey.DTO.OrderDTO;
import com.chvey.converter.Converter;
import com.chvey.domain.Order;
import com.chvey.domain.User;
import com.chvey.service.OrderService;
import com.chvey.service.UserService;
import com.chvey.validator.BadRequest;
import com.chvey.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private Converter converter;
    @Autowired
    private Validator validator;

    @RequestMapping("/order")
   /* public String showOrder(Principal principal, ModelMap model, @RequestParam String products) {
        Order order = orderService.createOrder(principal.getName(), products);
        OrderDTO orderDTO = converter.convertOrderToOrederDTO(order);
        model.addAttribute("order", orderDTO);
        return "order";
    }*/
    public ModelAndView showOrder(Principal principal,@RequestParam String products){
        if(!validator.validateProducts(products)){
            return new ModelAndView();
        }
        Order order = orderService.createOrder(principal.getName(), products);
        OrderDTO orderDTO = converter.convertOrderToOrederDTO(order);
        return new ModelAndView("order","order",orderDTO);
    }
}
