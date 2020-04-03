package com.example.testtask.controllers;

import com.example.testtask.dao.UserRequestService;
import com.example.testtask.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * @author lobachev.nikolay
 */
@RestController
@RequestMapping("/requests-rest")
public class UserRequestRestController {
    private final UserRequestService userRequestService;

    @Autowired
    public UserRequestRestController(UserRequestService userRequestService) {
        this.userRequestService = userRequestService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody UserRequest userRequest) {
        try {
            userRequestService.create(userRequest);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRequest getAppUserRequest(@RequestParam(name = "user_request_id") Long user_request_id) {
        try {
            return userRequestService.findById(user_request_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}
