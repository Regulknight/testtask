package com.example.testtask.controllers;

import com.example.testtask.model.AppUser;
import com.example.testtask.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lobachev.nikolay 26.03.2020   19:17
 */

@Controller
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService service) {
        this.appUserService = service;
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        List<AppUser> result = appUserService.findAll();
        model.addAttribute(result);
        return "users";
    }

    @GetMapping("/users/requests")
    public String getUserRequests(@RequestParam(name="user_id") Long user_id, Model model){
        AppUser result = appUserService.findById(user_id).orElseThrow(() -> new RuntimeException());
        model.addAttribute(result.getUserRequests());
        return "user_requests";
    }
}
