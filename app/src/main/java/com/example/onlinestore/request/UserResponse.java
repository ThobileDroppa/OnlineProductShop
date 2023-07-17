package com.example.onlinestore.request;

import com.example.onlinestore.user.models.UserModel;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    // Find model object

    @SerializedName("users")
    private UserModel user;

    public UserModel getUser(){return user;}

    @Override
    public String toString() {
        return "UserResponse{" +
                "user=" + user +
                '}';
    }
}
