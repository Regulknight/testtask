package com.example.testtask.services;

import com.example.testtask.model.UserRequest;

import java.util.List;
import java.util.Optional;

/**
 * @author lobachev.nikolay 22.03.2020   22:24
 */

public interface UserRequestService {

    Optional<UserRequest> save(UserRequest userRequest);

    List<UserRequest> findAll();

    Optional<UserRequest> findById(Long id);
}
