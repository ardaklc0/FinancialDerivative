package com.test.demo.dto.request.role;

public class UpdateExistingRoleRequest {
    private String role;
    private Long id;

    public String getRole() {
        return role;
    }
    public Long getId() {
        return id;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
