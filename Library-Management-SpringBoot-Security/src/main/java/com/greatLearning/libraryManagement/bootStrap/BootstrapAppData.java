package com.greatLearning.libraryManagement.bootStrap;

import javax.transaction.Transactional;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatLearning.libraryManagement.entity.Role;
import com.greatLearning.libraryManagement.entity.User;
import com.greatLearning.libraryManagement.repository.UserRepository;

@Configuration
public class BootstrapAppData {

	private final UserRepository userJpaRepository;

	private final PasswordEncoder passwordEncoder;

	public BootstrapAppData(UserRepository userJpaRepository, PasswordEncoder passwordEncoder) {
		this.userJpaRepository = userJpaRepository;
		this.passwordEncoder = passwordEncoder;

	}

	@Transactional
	@EventListener(ApplicationReadyEvent.class)
	public void insertAppData(ApplicationReadyEvent event) {

		User testAdmin = new User("admin", this.passwordEncoder.encode("admin"));
		User testUser = new User("user", this.passwordEncoder.encode("user"));

		Role adminRole = new Role("ADMIN");
		Role userRole = new Role("USER");

		testAdmin.addRole(userRole);
		testAdmin.addRole(adminRole);

		testUser.addRole(userRole);

		this.userJpaRepository.save(testAdmin);
		this.userJpaRepository.save(testUser);
		System.out.println("Test Credentials inserted... ");

	}
}