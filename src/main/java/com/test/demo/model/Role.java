package com.test.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    public Role(String role){
        this.role = role;
    }

    public Role(){}

    public Long getId() {
        return id;
    }

    public void setId(Long user_role_id) {
        this.id = user_role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String user_role_name) {
        this.role = user_role_name;
    }

    @Override
    public String toString() {
        return
                "\nUserRole{" +
                        "\nuser_role_id=" + id +
                        "\nuser_role_name='" + role +
                        "}\n";
    }
}
