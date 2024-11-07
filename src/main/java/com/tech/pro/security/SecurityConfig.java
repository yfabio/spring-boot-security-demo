package com.tech.pro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.tech.pro.security.impl.AuthenticationProviderImpl;

@Configuration
public class SecurityConfig {

	@Autowired
	private AuthenticationProviderImpl authProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors(c -> c.disable())
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(c -> {	
				c.requestMatchers("/js/**").permitAll();
			c.requestMatchers("/home", "/", "/login", "/register", "/new-user", "/h2-console/**").permitAll();
			c.requestMatchers("/main/**").authenticated();
		}).headers(header -> {
			header.frameOptions(op -> op.sameOrigin());
		}).formLogin(f -> {
			f.loginPage("/login");
			f.successForwardUrl("/main");			
			f.failureUrl("/login?error=true").permitAll();			
		}).authenticationProvider(authProvider).logout(l -> {			
			l.logoutSuccessUrl("/home").invalidateHttpSession(true).permitAll();
		});

		return http.build();
	}

}
