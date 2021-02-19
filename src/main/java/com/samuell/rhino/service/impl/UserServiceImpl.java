package com.samuell.rhino.service.impl;

import com.samuell.rhino.controller.form_validation.ValidationHelpers;
import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.UserDto;
import com.samuell.rhino.model.mapper.UserMapper;
import com.samuell.rhino.repository.UserRepository;
import com.samuell.rhino.service.UserService;
import com.samuell.rhino.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                .filter(user -> !user.isWas_deleted())
                .map(user -> userMapper.toUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsersForUserDetails() {
        return userRepository.findAll().stream()
                .filter(user -> !user.isWas_deleted())
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer userId) {
        return userMapper.toUserDto(userRepository.findById(userId).orElse(null));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return  userMapper.toUserDto(userRepository.findByUsername(username));
    }

    @Override
    public User addUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setCreated_at(Timestamp.from(Instant.now()));

        if (user.getName() == null ||
            user.getEmail() == null ||
            user.getUsername() == null ||
            user.getPassword() == null ||
            user.getRole() == null){
            return user;
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElse(new User());

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setTelephone_number(userDto.getTelephone_number());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setEdited_at(Timestamp.from(Instant.now()));

        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Integer userId) {
        String pattern = RandomString.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
        User user = userRepository.findById(userId).orElse(new User());

        user.setWas_deleted(true);
        user.setPassword(new RandomString(10, new SecureRandom(), pattern).toString());
        user.setUsername(new RandomString(10, new SecureRandom(), pattern).toString());

        return userRepository.save(user);
    }

    @Override
    public Map<String,String> validateUser(UserDto userDto){
        Map<String,String> errors = new HashMap<>();

        if(userDto.getName() == null || !userDto.getName().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("name","Zadajte meno");

        if(userDto.getEmail() == null || !userDto.getEmail().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("email","Zadajte e-mail");
        else if(!userDto.getEmail().matches(ValidationHelpers.VALID_EMAIL_ADDRESS_REGEX.pattern()))
            errors.put("email","Zadajte platný e-mail");
        else if(userRepository.findAll()
                .stream()
                .filter(user ->
                        !user.getId().equals(userDto.getId()) &&
                        user.getEmail().equals(userDto.getEmail()))
                .count() >= 1){
            errors.put("email","E-mail je obsahený");
        }

        if(userRepository.findAll()
                .stream()
                .filter(user -> !user.getId().equals(userDto.getId()) && user.getUsername().equals(userDto.getUsername()))
                .count() >= 1){
            errors.put("username","Login je obsadený");
        }

        if(userDto.getUsername() == null || !userDto.getUsername().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("username","Zadajte login");
        if(userDto.getPassword() == null || !userDto.getPassword().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("password","Zadajte heslo");
        if(userDto.getRole() == null || !userDto.getRole().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("role","Vyberte rolu");

        return errors;
    }
}
