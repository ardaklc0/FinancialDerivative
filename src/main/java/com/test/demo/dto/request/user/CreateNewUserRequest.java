package com.test.demo.dto.request.user;

public class CreateNewUserRequest {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }
    public void setPassword(String user_password) {
        this.password = user_password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String user_name) {
        this.username = user_name;
    }
}
