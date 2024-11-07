package com.tech.pro.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tech.pro.model.User;
import com.tech.pro.repository.UserRepository;
import com.tech.pro.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
		
	private UserRepository userRepository;
		
	private PasswordEncoder passwordEncoder;
		
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void hashPassword(User user) {
		String hashPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashPassword);
		userRepository.save(user);
	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		return userRepository.findByEmail(username);
	}

	@Override
	public boolean verify(String pwd, String hashPassword) {
		return passwordEncoder.matches(pwd, hashPassword);
	}
}
