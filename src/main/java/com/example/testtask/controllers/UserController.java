package com.example.testtask.controllers;

import com.example.testtask.model.User;
import com.example.testtask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lobachev.nikolay 26.03.2020   19:17
 */

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> result = userService.findAll();
        model.addAttribute(result);
        return "users";
    }

    @GetMapping("/users/requests")
    public String getUserRequests(@RequestParam(name="user_id") Long user_id, Model model){
        User result = userService.findById(user_id).orElseThrow(() -> new RuntimeException());
        model.addAttribute(result.getUserRequests());
        return "user_requests_for_one_user";
    }

    @GetMapping("/create_user")
    public String getCreateUser(){
        return "create_user";
    }

    @PostMapping("/create_user")
    public ResponseEntity<User> createUser(@RequestParam(name = "first_name") String firstName, @RequestParam(name = "last_name") String lastName){
        return userService.save(new User(firstName, lastName)).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).orElseThrow(() -> new RuntimeException());
    }

    @DeleteMapping("/delete_user")
    public String deleteUser(@RequestParam(name = "user_id") Long user_id){
        User user = userService.findById(user_id).orElseThrow(RuntimeException::new);
        userService.delete(user);
        return "users";
    }
}
