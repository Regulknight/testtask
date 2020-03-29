package com.example.testtask.controllers;

import com.example.testtask.model.User;
import com.example.testtask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lobachev.nikolay 26.03.2020   19:17
 */

@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        Iterable<User> result = userRepository.findAll();
        model.addAttribute(result);
        return "users";
    }

    @GetMapping("/users/requests")
    public String getUserRequests(@RequestParam(name="user_id") Long user_id, Model model){
        User result = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException());
        model.addAttribute(result.getUserRequests());
        return "user_requests_for_one_user";
    }

    @GetMapping("/create_user")
    public String getCreateUser(){
        return "create_user";
    }

    @PostMapping("/create_user")
    public User createUser(@RequestParam(name = "first_name") String firstName, @RequestParam(name = "last_name") String lastName){
        return userRepository.save(new User(firstName, lastName));
    }

    @DeleteMapping("/delete_user")
    public String deleteUser(@RequestParam(name = "user_id") Long user_id){
        User user = userRepository.findById(user_id).orElseThrow(RuntimeException::new);
        userRepository.delete(user);
        return "users";
    }
}
