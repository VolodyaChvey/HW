package com.chvey.service;

import com.chvey.domain.User;
import com.chvey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> getCurrentUser(Principal principal) {
        return Optional.ofNullable(userRepository.findUserByEmail(principal.getName()));
    }

    public Optional<List<User>> getAllManagers() {
        return Optional.ofNullable(userRepository.findAllManagers());
    }

    public Optional<List<User>> getAllEngineers() {
        return Optional.ofNullable(userRepository.findAllEngineers());
    }
}
