package com.cqu.springboot.dto;

import com.cqu.springboot.entity.User;

import java.io.Serializable;

public class LoginUserDTo implements Serializable {

    private User user;

    public LoginUserDTo() {
    }

    public LoginUserDTo(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "LoginUserDTo{" +
                "user=" + user +
                '}';
    }
}