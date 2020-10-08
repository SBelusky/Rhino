package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.UserDto;
import com.samuell.rhino.model.mapper.UserMapper;
import com.samuell.rhino.repository.UserRepository;
import com.samuell.rhino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> userMapper.userToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer id) {
        return userMapper.userToUserDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public User addUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user.setCreated_at(Timestamp.from(Instant.now()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, UserDto userDto) {
        User user = userRepository.findById(id).orElse(new User());

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setTelephone_number(userDto.getTelephone_number());
        user.setLogin_name(userDto.getLogin_name());
        user.setLogin_password(userDto.getLogin_password());
        user.setRole(userDto.getRole());
        user.setEdited_at(Timestamp.from(Instant.now()));

        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(new User());
        user.setWas_deleted(true);

        return userRepository.save(user);
    }
}
