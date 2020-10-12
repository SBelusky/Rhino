package com.samuell.rhino.controller;

import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.UserDto;
import com.samuell.rhino.model.mapper.UserMapper;
import com.samuell.rhino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserMapper movieMapper;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> usersDtoList = userService.getAllUsers();

        return new ResponseEntity<>(usersDtoList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
        UserDto userDto = userService.getUserById(id);

        if(userDto == null){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        User user = userService.addUser(userDto);

        if(user == null){
            return new ResponseEntity<>("Error while creating user",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body("User created with ID: "+ user.getId());
        }
    }

    @PostMapping("edit/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
        if(userService.getUserById(id) == null){
            return new ResponseEntity<>("User not found",HttpStatus.PRECONDITION_FAILED);
        }
        else {
            User user = userService.updateUser(id,userDto);

            return ResponseEntity.status(HttpStatus.OK).body("User with ID: "+ user.getId() + " was updated");
        }
    }

    @DeleteMapping("{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {

        if(userService.getUserById(id) == null){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        else {
            User user = userService.deleteUser(id);

            return ResponseEntity.status(HttpStatus.OK).body("User with ID: "+ user.getId() + " was deleted");
        }
    }
}
