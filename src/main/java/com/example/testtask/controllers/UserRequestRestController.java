package com.example.testtask.controllers;

import com.example.testtask.model.UserRequest;
import com.example.testtask.repositories.UserRequestRepository;
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
    private final UserRequestRepository userRequestRepository;

    @Autowired
    public UserRequestRestController(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRequest save(@RequestBody UserRequest userRequest) {
        return userRequestRepository.save(userRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRequest> getAppUserRequest(@RequestParam(name = "user_request_id") Long user_request_id){
        return userRequestRepository.findById(user_request_id)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseThrow(RuntimeException::new);
    }
}
