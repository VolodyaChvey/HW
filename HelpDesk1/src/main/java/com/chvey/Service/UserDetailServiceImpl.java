package com.chvey.Service;

import com.chvey.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static java.util.Objects.nonNull;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user= userService.getUserByEmail(email);
      if(nonNull(user)){
          return org.springframework.security.core.userdetails.User
                  .builder()
                  .username(user.getEmail())
                  .password(user.getPassword())
                  .authorities(getRole(user))
                  .build;
      }else {
          throw new UsernameNotFoundException(email);
      }

    }
}
