package com.example.testtask.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

/**
 * @author lobachev.nikolay
 */

public class UserRequest {
    private long id;

    private String title;
    private String description;
    private Date requestDate;

    @JsonBackReference
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User app_user) {
        this.user = app_user;
    }
}
