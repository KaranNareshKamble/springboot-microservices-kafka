package com.knk.department.service;

import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.knk.department.dto.UserCreatedEvent;
import com.knk.department.entity.Department;
import com.knk.department.exception.ResourceNotFoundException;
import com.knk.department.repository.DepartmentRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {
	

	
	private final DepartmentRepo departmentRepo;

	public Department saveDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentRepo.save(department);
	}

	public Department findDepartmentById(Long id) {
		// TODO Auto-generated method stub
		Department d= departmentRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Department Not Found with ID: "+id));
				
		return d;
	}
	
//	@KafkaListener(topics = "user-events",  groupId = "department-service-group")
//	public void kafkaMethod(UserCreatedEvent e) {
//		log.info("#####################in kafka method");
//
//		UserCreatedEvent uce= new UserCreatedEvent(e.userId(),
//				e.userName(), e.departmentId(), e.occuredAt());
//		log.info("########################## print kafka "
//				+ "########################"+uce);
//		
//	}
//	
//	@KafkaListener(topics = "user-events", groupId = "department-service-group2")
//	public void kafkaMethd2(UserCreatedEvent e) {
//		log.info("######## in kafkaMethd2" );
//		if (true) {
//	        throw new RuntimeException("############ Department processing failed!");
//	    }
//		log.info("kafka method 2 #########################");
//	}
	
}
