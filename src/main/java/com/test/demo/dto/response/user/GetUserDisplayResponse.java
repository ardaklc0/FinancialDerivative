package com.test.demo.dto.response.user;

public class GetUserDisplayResponse {
    private Long id;
    private String name;

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
