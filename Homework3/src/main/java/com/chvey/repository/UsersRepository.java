package com.chvey.repository;


import com.chvey.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UsersRepository {
    private List<User> users = new ArrayList<>();

    public User save (User user){
        users.add(user);
        return user;
    }
    public Optional<User> getUserByName (String userName){
        return users.stream()
                .filter(user -> userName.equals(user.getName()))
                .findFirst();
    }
}
