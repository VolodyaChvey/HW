package com.chvey.service;

import com.chvey.domain.User;
import com.chvey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {

        return userRepository.findUserByEmail(email);
    }

    public User getCurrentUser(Principal principal){
        return userRepository.findUserByEmail(principal.getName());
    }
}
