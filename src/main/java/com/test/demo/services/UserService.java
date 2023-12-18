package com.test.demo.services;

import com.test.demo.dto.request.user.CreateNewUserRequest;
import com.test.demo.model.User;
import com.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;

    public void save(CreateNewUserRequest user){
        User newUser = new User();
        newUser.setUserName(user.getName());
        newUser.setUserPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(newUser);
    }
    public User findByName(String name){
        return repository.findByName(name);
    }
}
