package com.tech.pro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.pro.model.User;
import com.tech.pro.service.UserService;
import com.tech.pro.utils.PwdBuffer;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private UserService userService;
	
	private PwdBuffer starterRoles;

	@RequestMapping(value = { "/home", "/" })
	public String home(Model model) {
		List<User> users = userService.getAllUsers().stream().map(user -> {
			user.setConfirmPassword(starterRoles.get(user.getName()));
			return user;
		}).collect(Collectors.toCollection(ArrayList::new));

		model.addAttribute("users", users);
		return "index";
	}

}
