package com.example.testtask.controllers;

import com.example.testtask.model.User;
import com.example.testtask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("userList", result);
        return "users";
    }

    @GetMapping("/users/requests")
    public String getUserRequests(@RequestParam(name="user_id") Long user_id, Model model){
        User result = userRepository.findById(user_id).orElseThrow(RuntimeException::new);
        model.addAttribute(result.getUserRequests());
        return "user_requests_for_one_user";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/edit_user/{user}")
    public String getEditUser(@PathVariable(name = "user") User user, Model model){
        model.addAttribute("userObject", user);
        return "edit_user";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/edit_user")
    public String editUser(@RequestParam(name = "user_id") User user,
                           @RequestParam(name = "first_name") String firstName,
                           @RequestParam(name = "last_name") String lastName){
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
        return "redirect:/users";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam(name = "user_id") User user){
        userRepository.delete(user);
        return "redirect:/users";
    }
}
