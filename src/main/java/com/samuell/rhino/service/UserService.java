package com.samuell.rhino.service;

import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.UserDto;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

public interface UserService{
    @Nullable
    List<UserDto> getAllUsers();

    @Nullable
    List<User> getAllUsersForUserDetails();

    @Nullable
    UserDto getUserById(Integer userId);

    @Nullable
    UserDto getUserByUsername(String username);

    @Nullable
    User addUser(UserDto userDto);

    @Nullable
    User updateUser(Integer userId, UserDto userDto);

    @Nullable
    User deleteUser(Integer userId);

    @Nullable
    Map<String,String> validateUser(UserDto userDto);

}
