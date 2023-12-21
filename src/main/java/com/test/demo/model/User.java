package com.test.demo.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name = "client")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User(String user_name, String user_password, List<Role> user_roles){
        this.username = user_name;
        this.password = user_password;
        this.roles = user_roles;
    }
    public User(){}
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String user_password) {
        this.password = user_password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRole(List<Role> user_roles) {
        this.roles = user_roles;
    }

    @Override
    public String toString() {
        return
                "\nUser{" +
                        "\nuser_id=" + id +
                        "\nuser_name=" + username +
                        "\nuser_password='" + password + '\'' +
                        "\nuser_role=" + roles +
                        "}\n";
    }
}
