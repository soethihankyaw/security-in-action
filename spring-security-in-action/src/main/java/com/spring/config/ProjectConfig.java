package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class ProjectConfig{
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		var userDetailsService = new InMemoryUserDetailsManager();
		var bCryptPasswordEncoder = new BCryptPasswordEncoder();
		var user = User.withUsername("John")
				.password(bCryptPasswordEncoder.encode("12345"))
				.authorities("read")
				.build();
		
		userDetailsService.createUser(user);
		
		System.out.println(user.getPassword());
		return userDetailsService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	public AuthenticationManager aunAuthenticationManager(UserDeta)

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.anyRequest().permitAll();
		return http.build();
	}
}
