package com.test.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "client")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User(String user_name, String user_password, List<Role> user_roles){
        this.name = user_name;
        this.password = user_password;
        this.roles = user_roles;
    }
    public User(){}
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

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String user_password) {
        this.password = user_password;
    }

    public List<Role> getUserRoles() {
        return roles;
    }

    public void setUserRole(List<Role> user_roles) {
        this.roles = user_roles;
    }

    @Override
    public String toString() {
        return
                "\nUser{" +
                        "\nuser_id=" + id +
                        "\nuser_name=" + name +
                        "\nuser_password='" + password + '\'' +
                        "\nuser_role=" + roles +
                        "}\n";
    }
}
