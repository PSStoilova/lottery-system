package com.example.lottery.controller;

import com.example.lottery.users.UserService;
import com.example.lottery.users.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user){
       return userService.addUser(user);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return userService.fetchAllUsers();
    }
}
