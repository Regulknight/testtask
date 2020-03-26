package com.example.testtask.repositories;

import com.example.testtask.model.UserRequest;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lobachev.nikolay 22.03.2020   19:46
 */
public interface UserRequestRepository extends CrudRepository<UserRequest, Long> {
}
