package com.tech.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.pro.model.User;
import com.tech.pro.service.RoleService;
import com.tech.pro.service.UserService;
import com.tech.pro.utils.PwdBuffer;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegisterController {
	
	private RoleService roleService;
	
	private UserService userService;
	
	private PwdBuffer pwdBuffer;

	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("values", roleService.getAllRoles());
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping(value = "/new-user", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, Errors errors, Model model) {

		if (errors.hasErrors() || !user.doesPasswordMatch()) {			
			model.addAttribute("user", user);			
			model.addAttribute("values",roleService.getAllRoles());
			errors.rejectValue("confirmPassword", null,"password must match"); 
			return "register";
		} else {
			pwdBuffer.save(user.getName(), user.getPassword());
			userService.hashPassword(user);
		}

		return "redirect:home";
	}

}
