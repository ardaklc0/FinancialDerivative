package com.test.demo;

import com.test.demo.model.Role;
import com.test.demo.model.User;
import com.test.demo.repository.RoleRepository;
import com.test.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RoleRepository roleRepository, UserRepository userRepository) {
		return (args) -> {
			roleRepository.save(new Role("ROLE_ADMIN"));
			List<Role> roles = roleRepository.findAll();
			userRepository.save(new User("Jack", "Bauer", roles));
		};
	}

	//q: how to bcrypt password?
	//a: https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt


}

