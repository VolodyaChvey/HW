package com.chvey.converters;

import com.chvey.domain.User;
import com.chvey.domain.enums.Role;
import com.chvey.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {


    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().name());
        return userDto;
    }
    public User toEntity(UserDto userDto){
        User user= new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole(Role.valueOf(userDto.getRole().toUpperCase()));
        return user;
    }
}
