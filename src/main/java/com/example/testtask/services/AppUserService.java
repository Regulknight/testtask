package com.example.testtask.services;

import com.example.testtask.model.AppUser;

import java.util.List;
import java.util.Optional;

/**
 * TODO: comment
 *
 * @author lobachev.nikolay 22.03.2020   22:19
 */
public interface AppUserService {

    Optional<AppUser> save(AppUser user);

    void delete(AppUser appUser);

    List<AppUser> findAll();
}
