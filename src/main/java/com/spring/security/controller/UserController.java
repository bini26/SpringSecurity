package com.spring.security.controller;

import com.spring.security.models.User;
import com.spring.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;


    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getuser")
    public List<User> getAllUsers() {
        return this.service.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.service.getUser(username);
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {

        return this.service.addUser(user);
    }

}
