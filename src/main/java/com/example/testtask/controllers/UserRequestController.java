package com.example.testtask.controllers;

import com.example.testtask.dao.UserRequestService;
import com.example.testtask.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author lobachev.nikolay
 */
@Controller
public class UserRequestController {
    private final UserRequestService userRequestService;

    @Autowired
    public UserRequestController(UserRequestService userRequestService) {
        this.userRequestService = userRequestService;
    }

    @GetMapping("/requests")
    public String getUserRequests(Model model) {
        try {
            model.addAttribute("usersRequests", userRequestService.findAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "users_requests";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/edit_user_request/{user_request_id}")
    public String getEditUser(@PathVariable(name = "user_request_id") Long userRequestId, Model model) {
        UserRequest userRequest = null;
        try {
            userRequest = userRequestService.findById(userRequestId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("userRequest", userRequest);
        return "edit_user_request";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/edit_user_request")
    public String editUser(@RequestParam(name = "user_request_id") Long userRequestId,
                           @RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description,
                           @RequestParam(name = "request_date") String dateString) throws ParseException {
        UserRequest userRequest = null;
        try {
            userRequest = userRequestService.findById(userRequestId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userRequest != null) {
            userRequest.setTitle(title);
            userRequest.setDescription(description);
            if (!StringUtils.isEmpty(dateString)) {
                userRequest.setRequestDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
            }
            try {
                userRequestService.save(userRequest);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "redirect:/requests";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/delete_user_request")
    public String deleteUser(@RequestParam(name = "user_request_id") Long userRequestId) {
        try {
            userRequestService.delete(userRequestId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/requests";
    }
}
