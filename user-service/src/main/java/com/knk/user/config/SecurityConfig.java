package com.knk.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.knk.user.security.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtFilter jwtFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable())
		.formLogin(form -> form.disable())
		.httpBasic(basic -> basic.disable())
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.exceptionHandling(ex -> ex
	            .authenticationEntryPoint(
	                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
	            )
	        )
		.authorizeHttpRequests(auth->auth.requestMatchers("/auth/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/users/").permitAll()
				.anyRequest().authenticated())
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}
}
