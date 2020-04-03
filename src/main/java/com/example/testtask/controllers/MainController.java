package com.example.testtask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lobachev.nikolay
 */

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String getMainPage() {
        return "main";
    }
}
