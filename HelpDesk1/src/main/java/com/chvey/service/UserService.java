package com.chvey.service;

import com.chvey.domain.User;

interface UserService {
   User getUserByEmail(String email);
}
