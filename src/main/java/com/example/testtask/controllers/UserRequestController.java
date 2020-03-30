package com.example.testtask.controllers;

import com.example.testtask.model.UserRequest;
import com.example.testtask.repositories.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author lobachev.nikolai 29.03.2020   01:16
 */
@Controller
public class UserRequestController {
    private final UserRequestRepository userRequestRepository;

    @Autowired
    public UserRequestController(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }

    @GetMapping("/requests")
    public String getUserRequests(Model model){
        model.addAttribute("usersRequests", userRequestRepository.findAll());
        return "users_requests";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/edit_user_request/{user_request}")
    public String getEditUser(@PathVariable(name = "user_request") UserRequest userRequest, Model model){
        model.addAttribute("userRequest", userRequest);
        return "edit_user_request";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/edit_user_request")
    public String editUser(@RequestParam(name = "user_request_id") UserRequest userRequest,
                           @RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description,
                           @RequestParam(name = "request_date") String dateString) throws ParseException {
        userRequest.setTitle(title);
        userRequest.setDescription(description);
        if (!StringUtils.isEmpty(dateString)) {
            userRequest.setRequestDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
        }
        userRequestRepository.save(userRequest);
        return "redirect:/requests";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/delete_user_request")
    public String deleteUser(@RequestParam(name = "user_request_id") UserRequest userRequest){
        userRequestRepository.delete(userRequest);
        return "redirect:/requests";
    }
}
