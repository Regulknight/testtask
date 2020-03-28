package com.example.testtask.controllers;

import com.example.testtask.model.AppUser;
import com.example.testtask.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author lobachev.nikolay 22.03.2020   19:50
 */

@RestController
@RequestMapping("/rest")
public class AppUserRestController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserRestController(AppUserService service) {
        this.appUserService = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> save(@RequestBody AppUser user) {
        return appUserService.save(user).map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseThrow(RuntimeException::new);
    }

    @DeleteMapping
    public ResponseEntity<AppUser> delete(@RequestBody AppUser user) {
        appUserService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/users")
    public List<AppUser> getUsers(){
        return appUserService.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public AppUser getUser(@RequestParam(name = "user_id") Long user_id){
        return appUserService.findById(user_id).orElseThrow(() -> new RuntimeException());
    }
}
