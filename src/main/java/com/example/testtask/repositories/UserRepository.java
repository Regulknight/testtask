package com.example.testtask.repositories;

import com.example.testtask.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lobachev.nikolay 22.03.2020   19:22
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
