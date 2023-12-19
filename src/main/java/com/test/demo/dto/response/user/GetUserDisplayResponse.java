package com.test.demo.dto.response.user;

public class GetUserDisplayResponse {
    private Long id;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String user_name) {
        this.username = user_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long user_id) {
        this.id = user_id;
    }
}
