package com.knk.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knk.user.entity.Admin;
import com.knk.user.entity.User;
import com.knk.user.service.UserService;
import com.knk.user.vo.ResponseTempVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
	
	private final UserService userService;

	@PostMapping("/")
	public ResponseEntity<User> addUser(@RequestBody User user) {
//		return userService.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.addUser(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
//		return userService.getByID(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.getByID(id));
		
	}
	
	@GetMapping("/userAndDepById/{id}")
	public ResponseTempVO getUserWithDepartment(@PathVariable("id") Long userId) {
		log.info("inside getUserWithDepartment controller");
		return userService.getUserWithDepartment(userId);
	}
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> addAdmin1(@RequestBody Admin adminInfo){
		log.info("In addAdmin controller");
//		return userService.addAdmin(adminInfo);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.addAdmin(adminInfo));
	}
	
	@PostMapping("/men")
	public Admin addAdmin(@RequestBody Admin adminInfo){
		log.info("In addAdmin controller");
//		return userService.addAdmin(adminInfo);
		Admin a= new Admin();
		return a;
	}
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<Admin> getAdmin( @PathVariable Long id){
		log.info("In addAdmin controller");

		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.getAdminById(id));
	}
}
