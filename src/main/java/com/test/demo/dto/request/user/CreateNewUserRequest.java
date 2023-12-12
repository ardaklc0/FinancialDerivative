package com.test.demo.dto.request.user;

public class CreateNewUserRequest {
    private String name;

    public String getUserName() {
        return name;
    }

    public void setUserName(String user_name) {
        this.name = user_name;
    }
}
