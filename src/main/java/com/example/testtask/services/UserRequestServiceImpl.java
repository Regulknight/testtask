package com.example.testtask.services;

import com.example.testtask.model.AppUser;
import com.example.testtask.model.UserRequest;
import com.example.testtask.repositories.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lobachev.nikolay 22.03.2020   22:25
 */

@Service
public class UserRequestServiceImpl implements UserRequestService{
    private final UserRequestRepository userRequestRepository;

    @Autowired
    public UserRequestServiceImpl(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }

    @Override
    public Optional<UserRequest> save(UserRequest userRequest) {
        return Optional.of(userRequestRepository.save(userRequest));
    }

    @Override
    public List<UserRequest> findAll() {
        return (List<UserRequest>) userRequestRepository.findAll();
    }
}
