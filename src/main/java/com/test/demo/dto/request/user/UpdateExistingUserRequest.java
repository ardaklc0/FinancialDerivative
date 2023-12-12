package com.test.demo.dto.request.user;

public class UpdateExistingUserRequest {
    private String name;
    private Long id;

    public String getUserName() {
        return name;
    }

    public void setUserName(String user_name) {
        this.name = user_name;
    }

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long user_id) {
        this.id = user_id;
    }

}
