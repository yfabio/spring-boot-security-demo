package com.tech.pro.security.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.tech.pro.model.User;
import com.tech.pro.service.UserService;


@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		if(auth!=null) {
			
			String username = auth.getName();
			String pwd = auth.getCredentials().toString();
					
			Optional<User> user = userService.findUserByUsername(username);	
			
			if(user.isPresent()) {
				if(userService.verify(pwd,user.get().getPassword())) {
					return new UsernamePasswordAuthenticationToken(username,pwd,getGrantedAuthory(user.get()));
				}
			}						
			
		}		
		
		throw new BadCredentialsException("invalid credential");
	}		

	@Override
	public boolean supports(Class<?> authentication) {		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private List<GrantedAuthority> getGrantedAuthory(User user) {
		return user.getRoles().stream().map(e -> new SimpleGrantedAuthority(e.getRole()))
				.collect(Collectors.toCollection(ArrayList::new));
	}

}
