package com.example.testtask.controllers;

import com.example.testtask.model.User;
import com.example.testtask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lobachev.nikolay 30.03.2020   00:38
 */

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String createUser(User user){

        return "redirect:/login";
    }
}
