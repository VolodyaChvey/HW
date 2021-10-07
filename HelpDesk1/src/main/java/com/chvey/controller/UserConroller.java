package com.chvey.controller;

import com.chvey.converters.UserConverter;
import com.chvey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/users")
public class UserConroller {
    private UserService userService;
    private UserConverter userConverter;

    @Autowired
    public UserConroller(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping(value = "/current")
    public ResponseEntity getCurrentUser(Principal principal) {
        return userService.getCurrentUser(principal)
                .map(user -> ResponseEntity.ok(userConverter.toDto(user)))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
