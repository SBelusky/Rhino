package com.samuell.rhino.service;

import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.UserDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserService{
    @Nullable
    List<UserDto> getAllUsers();

    @Nullable
    UserDto getUserById(Integer id);

    @Nullable
    User addUser(UserDto userDto);

    @Nullable
    User updateUser(Integer id, UserDto userDto);

    @Nullable
    User deleteUser(Integer id);
}
