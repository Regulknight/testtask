package com.example.testtask.repositories;

import com.example.testtask.model.AppUser;
import org.springframework.data.repository.CrudRepository;

/**
 * TODO: comment
 *
 * @author lobachev.nikolay 22.03.2020   19:22
 */
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

}
