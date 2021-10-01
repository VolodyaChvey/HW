package com.chvey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/")
    public String getWelcom() {
        System.out.println("hello");
        return "login";
    }

    @RequestMapping("/login")
    public String login(Principal principal) {
        System.out.println("login");
        if (principal != null) {
            return "hello";
        } else {
            System.out.println("hi");
            return "login";
        }
    }
}
