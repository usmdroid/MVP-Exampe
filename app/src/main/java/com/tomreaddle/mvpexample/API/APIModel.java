package com.tomreaddle.mvpexample.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIModel {
    @SerializedName("username")
    @Expose
    String username;
    @SerializedName("password")
    @Expose
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
