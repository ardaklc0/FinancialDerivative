package com.test.demo.dto.request.user;

public class CreateNewUserRequest {
    private String name;
    private String password;

    public String getPassword() {
        return password;
    }
    public void setPassword(String user_password) {
        this.password = user_password;
    }
    public String getName() {
        return name;
    }

    public void setName(String user_name) {
        this.name = user_name;
    }
}
