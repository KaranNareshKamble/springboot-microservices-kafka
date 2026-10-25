package com.knk.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knk.user.dto.AuthResponse;
import com.knk.user.dto.LoginRequest;
import com.knk.user.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest req) {
		String token=authService.login(req.userName(), req.password());
		return new AuthResponse(token);
	}
}
