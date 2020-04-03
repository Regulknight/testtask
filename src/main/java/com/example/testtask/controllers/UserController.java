package com.example.testtask.controllers;

import com.example.testtask.dao.UserService;
import com.example.testtask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

/**
 * @author lobachev.nikolay
 */

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        Iterable<User> result = null;
        try {
            result = userService.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("userList", result);
        return "users";
    }

    @GetMapping("/users/requests")
    public String getUserRequests(@RequestParam(name = "user_id") Long user_id, Model model) {
        User result = null;
        try {
            result = userService.findById(user_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (result != null)
            model.addAttribute(result.getUserRequests());
        return "user_requests_for_one_user";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/edit_user/{user_id}")
    public String getEditUser(@PathVariable(name = "user_id") Long user_id, Model model) {
        User user = null;
        try {
            user = userService.findById(user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("userObject", user);
        return "edit_user";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/edit_user")
    public String editUser(@RequestParam(name = "user_id") Long user_id,
                           @RequestParam(name = "first_name") String firstName,
                           @RequestParam(name = "last_name") String lastName) {
        User user = null;
        try {
            user = userService.findById(user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            try {
                userService.save(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "redirect:/users";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam(name = "user_id") Long user_id) {
        try {
            userService.delete(user_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/users";
    }
}
