package com.test.demo.dto.request.role;

public class UpdateExistingRoleRequest {
    private String role;
    private Long id;

    public String getRoleName() {
        return role;
    }
    public Long getRoleId() {
        return id;
    }
    public void setRoleName(String role) {
        this.role = role;
    }
    public void setRoleId(Long id) {
        this.id = id;
    }

}
