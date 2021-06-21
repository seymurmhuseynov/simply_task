package com.crbn.task.model.request;

public class RequestLogin {

    private String username;
    private String password;

    public RequestLogin() {
    }

    public String getUsername() {
        return username;
    }

    public RequestLogin setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RequestLogin setPassword(String password) {
        this.password = password;
        return this;
    }

}
