package com.test.demo.services;
import com.test.demo.dto.request.user.CreateNewUserRequest;
import com.test.demo.dto.request.user.UpdateExistingUserRequest;
import com.test.demo.model.Role;
import com.test.demo.model.User;
import com.test.demo.repository.RoleRepository;
import com.test.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    public void save(CreateNewUserRequest user){
        User newUser = new User();
        roleRepository.save(new Role("ROLE_USER"));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByRole("ROLE_USER"));
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(roles);
        userRepository.save(newUser);
    }
    @Transactional
    public void update(UpdateExistingUserRequest request, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(request.getUsername());
            userRepository.save(existingUser);
        }
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findByUsername(String name){
        return userRepository.findByUsername(name);
    }
    public User findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }
}
