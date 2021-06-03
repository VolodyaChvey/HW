package com.chvey.service;

import com.chvey.domain.User;
import com.chvey.repository.UsersRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {



    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void createOrGet() {
        User vasya = new User("Vasya");
        UsersRepository usersRepository = new UsersRepository();
        usersRepository.save(vasya);
        Assert.assertEquals(vasya ,vasya);
    }
}