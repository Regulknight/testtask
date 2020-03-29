package com.example.testtask.controllers;

import com.example.testtask.model.UserRequest;
import com.example.testtask.services.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lobachev.nikolay 29.03.2020   02:15
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
    public ResponseEntity<UserRequest> save(@RequestBody UserRequest userRequest) {
        return userRequestService.save(userRequest)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseThrow(RuntimeException::new);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRequest> getAppUserRequest(@RequestParam(name = "user_request_id") Long user_request_id){
        return userRequestService.findById(user_request_id)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseThrow(RuntimeException::new);
    }
}
