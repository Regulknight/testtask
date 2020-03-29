package com.example.testtask.controllers;

import com.example.testtask.model.Role;
import com.example.testtask.model.User;
import com.example.testtask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

/**
 * @author lobachev.nikolay 30.03.2020   00:38
 */

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String createUser(User user, Model model){
        User databaseUser = userRepository.findByUsername(user.getUsername());
        if(databaseUser != null){
            String message = "User already exists!";
            model.addAttribute("resultMessage", message);
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
