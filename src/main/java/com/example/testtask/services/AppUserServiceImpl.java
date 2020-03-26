package com.example.testtask.services;

import com.example.testtask.model.AppUser;
import com.example.testtask.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * TODO: comment
 *
 * @author lobachev.nikolay 22.03.2020   22:20
 */

@Service
public class AppUserServiceImpl implements AppUserService{
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Optional<AppUser> save(AppUser user) {
        return Optional.of(appUserRepository.save(user));
    }

    @Override
    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
    }

    @Override
    public List<AppUser> findAll() {
        return (List<AppUser>) appUserRepository.findAll();
    }
}
