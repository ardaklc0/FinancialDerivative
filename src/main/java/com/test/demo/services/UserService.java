package com.test.demo.services;

import com.test.demo.dto.request.user.CreateNewUserRequest;
import com.test.demo.model.Role;
import com.test.demo.model.User;
import com.test.demo.repository.RoleRepository;
import com.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    public void save(CreateNewUserRequest user){
        User newUser = new User();
        roleRepository.save(new Role("ROLE_USER"));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByRole("ROLE_USER"));
        newUser.setUserName(user.getName());
        newUser.setUserPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setUserRole(roles);
        repository.save(newUser);
    }
    public User findByName(String name){
        return repository.findByName(name);
    }
}
