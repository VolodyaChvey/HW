package com.chvey.service;

import com.chvey.domain.User;
import com.chvey.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public User createOrGet(String userName) {
        return usersRepository.getUserByName(userName)
                .orElseGet(() -> createUser(userName));
    }

    private User createUser(String userName) {
        return usersRepository.save(userName);
    }
}
