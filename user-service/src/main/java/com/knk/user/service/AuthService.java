package com.knk.user.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.knk.user.repository.UserRepo;
import com.knk.user.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public String login(String userName, String password) {
		// TODO Auto-generated method stub
		
		var user=userRepo.findByUserName(userName).orElseThrow(()->
		new BadCredentialsException("Invalid Credentials"));
		
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Invalid Credentials");
		}
		return jwtService.generateToken(userName);
	}

}
// step 9 in chat GPT