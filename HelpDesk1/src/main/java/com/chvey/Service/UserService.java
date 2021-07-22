package com.chvey.Service;

import com.chvey.domain.User;

interface UserService {
   User getUserByEmail(String email);
}
