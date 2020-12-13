package com.samuell.rhino.service.impl;

import com.samuell.rhino.controller.form_validation.ValidationHelpers;
import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.UserDto;
import com.samuell.rhino.model.mapper.UserMapper;
import com.samuell.rhino.repository.UserRepository;
import com.samuell.rhino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EmbeddedId;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
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
    public UserDto getUserById(Integer userId) {
        return userMapper.toUserDto(userRepository.findById(userId).orElse(null));
    }

    @Override
    public User addUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setCreated_at(Timestamp.from(Instant.now()));

        if (user.getName() == null ||
            user.getEmail() == null ||
            user.getLogin_name() == null ||
            user.getLogin_password() == null ||
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
        user.setLogin_name(userDto.getLogin_name());
        user.setLogin_password(userDto.getLogin_password());
        user.setRole(userDto.getRole());
        user.setEdited_at(Timestamp.from(Instant.now()));

        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElse(new User());
        user.setWas_deleted(true);

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
                .filter(user -> !user.getId().equals(userDto.getId()) && user.getLogin_name().equals(userDto.getLogin_name()))
                .count() >= 1){
            errors.put("login_name","Login je obsadený");
        }

        if(userDto.getLogin_name() == null || !userDto.getLogin_name().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("login_name","Zadajte login");
        if(userDto.getLogin_password() == null || !userDto.getLogin_password().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("login_password","Zadajte heslo");
        if(userDto.getRole() == null || !userDto.getRole().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("role","Vyberte rolu");

        return errors;
    }
}
