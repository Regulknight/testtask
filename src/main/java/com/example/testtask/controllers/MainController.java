package com.example.testtask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lobachev.nikolay 29.03.2020   23:55
 */

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String getMainPage(){
        return "main";
    }
}
