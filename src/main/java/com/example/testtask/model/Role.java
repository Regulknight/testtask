package com.example.testtask.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * @author lobachev.nikolay
 */

public class Role implements GrantedAuthority {
    public final static Long DEFAULT_ROLE_ID = 1L;
    public final static String DEFAULT_ROLE_NAME = "ROLE_USER";

    private Long id;
    private String name;

    private Set<User> users;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<User> getUsers() {
        return users;
    }

    public void setUser(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
