package com.chvey.service;

import com.chvey.domain.User;
import com.chvey.repository.UsersRepository;

public class UserService {

    private UsersRepository usersRepository = new UsersRepository();

    public User createOrGet(String userName) {
        return usersRepository.getUserByName(userName)
                .orElseGet(() -> createUser(userName));
    }

    private User createUser(String userName) {
        return usersRepository.save(userName);
    }
}
