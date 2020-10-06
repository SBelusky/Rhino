package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.User;
import com.samuell.rhino.repository.UserRepository;
import com.samuell.rhino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
