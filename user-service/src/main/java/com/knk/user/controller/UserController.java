package com.knk.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knk.user.entity.User;
import com.knk.user.service.UserService;
import com.knk.user.vo.ResponseTempVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
	
	private final UserService userService;

	@PostMapping("/")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return userService.getByID(id);
	}
	
	@GetMapping("/userAndDepById/{id}")
	public ResponseTempVO getUserWithDepartment(@PathVariable("id") Long userId) {
		log.info("inside getUserWithDepartment controller");
		return userService.getUserWithDepartment(userId);
	}
}
