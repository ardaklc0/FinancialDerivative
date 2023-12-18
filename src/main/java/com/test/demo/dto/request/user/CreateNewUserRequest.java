package com.test.demo.dto.request.user;

public class CreateNewUserRequest {
    private String name;
    // TODO: Add password validation. There is a bug "rawPassword cannot be null"
    private String password;

    public String getUserPassword() {
        return password;
    }
    public void setUserPassword(String user_password) {
        this.password = user_password;
    }
    public String getUserName() {
        return name;
    }

    public void setUserName(String user_name) {
        this.name = user_name;
    }
}
