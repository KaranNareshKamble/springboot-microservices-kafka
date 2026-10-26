package com.knk.department.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knk.department.entity.Department;
import com.knk.department.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
	
	private final DepartmentService departmentService;
	
	@PostMapping("/")
	public ResponseEntity<Department>  saveDepartment(@RequestBody Department department) {
		log.info("inside saveDepartment controller ");
//		return departmentService.saveDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(departmentService.saveDepartment(department));
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Department> findDepartmentById(@PathVariable("id") Long id) {
		log.info("2 inside findDepartmentById controller ");
//		return departmentService.findDepartmentById(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(departmentService.findDepartmentById(id));
	}
}
