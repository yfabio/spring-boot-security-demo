package com.tech.pro.service;

import java.util.List;
import java.util.Optional;

import com.tech.pro.model.User;

public interface UserService {

	List<User> getAllUsers();

	void hashPassword(User user);

	Optional<User> findUserByUsername(String username);

	boolean verify(String pwd, String password);

}
