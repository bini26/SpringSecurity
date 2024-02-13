package com.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/public")
@RestController
public class HomeConroller {

    @GetMapping("")
public String home(){

        return "Home Page";
}

@GetMapping("/login")
public String login(){

        return "Login   Page";
}
}
