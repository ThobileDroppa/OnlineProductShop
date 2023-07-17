package com.example.onlinestore.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginSuccess {

    private String name;
    private String email;
    private String token;

    public LoginSuccess(String name, String email, String token) {
        this.name = name;
        this.email = email;
        this.token = token;
    }

    public LoginSuccess() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
