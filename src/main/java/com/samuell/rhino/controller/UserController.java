package com.samuell.rhino.controller;

import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.UserDto;
import com.samuell.rhino.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@RestController
@RequestMapping("api/")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    Matcher matcher;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> usersDtoList = userService.getAllUsers();

        return new ResponseEntity<>(usersDtoList, HttpStatus.OK);
    }

    @GetMapping("bug-user")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllUsersForAllRoles() {
        List<UserDto> usersDtoList = userService.getAllUsers();

        return new ResponseEntity<>(usersDtoList, HttpStatus.OK);
    }

    @GetMapping("detail/user/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
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

    @GetMapping("detail/logged-user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getLoggedUserById(@PathVariable("id") Integer id) {
        UserDto userDto = userService.getUserById(id);

        if(userDto == null){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
    }

    @PostMapping("logged-user")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getLoggedUserByUsername(@RequestBody UserDto userDto) {
        UserDto loggedUser = userService.getUserByUsername(userDto.getUsername());

        if(loggedUser == null){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(loggedUser, HttpStatus.OK);
        }
    }

    @PostMapping("add/user")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        Map<String,String> errors = userService.validateUser(userDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            User user = userService.addUser(userDto);

            return ResponseEntity.status(HttpStatus.CREATED).body("User created with ID: "+ user.getId());
        }
    }

    @PostMapping("edit/user/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
        Map<String,String> errors = userService.validateUser(userDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            User user = userService.updateUser(id,userDto);

            return ResponseEntity.status(HttpStatus.OK).body("User with ID: "+ user.getId() + " was updated");
        }
    }

    @PostMapping("edit/logged-user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateLoggedUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
        Map<String,String> errors = userService.validateUser(userDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            User user = userService.updateUser(id,userDto);

            return ResponseEntity.status(HttpStatus.OK).body("User with ID: "+ user.getId() + " was updated");
        }
    }

    @DeleteMapping("delete/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
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
