package com.samuell.rhino.service;

import com.samuell.rhino.model.User;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserService{
    @Nullable
    List<User> getAllUsers();

    @Nullable
    User getUserById(int id);

    @Nullable
    User addUser(User user);
}
