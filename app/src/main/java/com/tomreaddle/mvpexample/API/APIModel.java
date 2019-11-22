package com.tomreaddle.mvpexample.API;

public class APIModel {
    String username;
    String password;

    public APIModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
