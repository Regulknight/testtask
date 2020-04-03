package com.example.testtask.controllers;

import com.example.testtask.dao.UserService;
import com.example.testtask.model.Role;
import com.example.testtask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.Collections;

/**
 * @author lobachev.nikolay
 */

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String createUser(User user, Model model) {
        User databaseUser = null;
        try {
            databaseUser = userService.findByUsername(user.getUsername());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (databaseUser != null) {
            String message = "User already exists!";
            model.addAttribute("resultMessage", message);
            return "registration";
        }

        user.setActive(true);
        Role defaultRole = new Role(Role.DEFAULT_ROLE_ID, Role.DEFAULT_ROLE_NAME);
        user.setRoles(Collections.singleton(defaultRole));

        try {
            userService.create(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "redirect:/login";
    }
}
