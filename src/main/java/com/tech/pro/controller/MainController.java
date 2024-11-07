package com.tech.pro.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/main")
	public void main(Model model, Authentication auth) {
		model.addAttribute("username", auth.getName());

		List<? extends GrantedAuthority> list = auth.getAuthorities().stream().toList();

		model.addAttribute("roles", list);
	}

}
