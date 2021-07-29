package com.chvey.converters;

import com.chvey.domain.User;
import com.chvey.dto.UserDto;
import com.chvey.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private UserDto userDto;
    private UserService userService;
    private User user;

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
