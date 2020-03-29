package com.example.testtask.services;

import com.example.testtask.model.User;

import java.util.List;
import java.util.Optional;

/**
 * TODO: comment
 *
 * @author lobachev.nikolay 22.03.2020   22:19
 */
public interface UserService {

    Optional<User> save(User user);

    void delete(User appUser);

    List<User> findAll();

    Optional<User> findById(Long id);
}
