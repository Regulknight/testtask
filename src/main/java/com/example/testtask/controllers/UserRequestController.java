package com.example.testtask.controllers;

import com.example.testtask.services.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lobachev.nikolai 29.03.2020   01:16
 */
@Controller
public class UserRequestController {
    private final UserRequestService userRequestService;

    @Autowired
    public UserRequestController(UserRequestService userRequestService) {
        this.userRequestService = userRequestService;
    }

    @GetMapping("/requests")
    public String getUserRequests(Model model){
        model.addAttribute(userRequestService.findAll());
        return "users_requests";
    }
}
