package com.knk.user.service;

import java.time.Instant;

import org.jspecify.annotations.Nullable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.knk.user.dto.UserCreatedEvent;
import com.knk.user.entity.Admin;
import com.knk.user.entity.User;
import com.knk.user.exception.ResourceNotFoundException;
import com.knk.user.repository.AdminRepo;
import com.knk.user.repository.UserRepo;
import com.knk.user.vo.Department;
import com.knk.user.vo.ResponseTempVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	private static final String USER_EVENT_TOPIC="user-events";
	private final UserRepo userRepo;
	private final RestTemplate restTemplate;
	private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;
	private final PasswordEncoder passwordEncoder;
	private final AdminRepo adminRepo;

	public User addUser(User user) {
		// TODO Auto-generated method stub
		log.info("################################# addUser");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser= userRepo.save(user);
		
		UserCreatedEvent userCreatedEvent= new UserCreatedEvent(
				savedUser.getUserId(), savedUser.getUserName(),
				savedUser.getDepartmentId(), Instant.now()
				);
//log.info("#######################################before kafka send");
//		
//kafkaTemplate.send(USER_EVENT_TOPIC, userCreatedEvent).whenComplete((result, ex)->{
//	if(ex!=null) {
//		log.info("#####Error in Kafka send#####");
//	}else {
//		log.info("Kafka send successfully: topic:{}, partition:{}, offset:{} ",
//				result.getRecordMetadata().topic(), result.getRecordMetadata().partition(),
//				result.getRecordMetadata().offset());
//	}
//});
//
//
//log.info("########################################after kafka send");
		return savedUser;
	}

	public User getByID(Long id) {
		// TODO Auto-generated method stub
		User u= userRepo.findById(id).orElseThrow(()->
		new ResourceNotFoundException("User Not Found for ID: "+id));
		return u;
	}

	public ResponseTempVO getUserWithDepartment(Long userId) {
		// TODO Auto-generated method stub
		
		ResponseTempVO vo= new ResponseTempVO();
		User u=userRepo.findByUserId(userId);
		Department d= restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+u.getDepartmentId()
				, Department.class);
		vo.setUser(u);
		vo.setDepartment(d);
		return vo;
	}

	public Admin addAdmin(Admin adminInfo) {
		// TODO Auto-generated method stub
		Admin savedAdmin=adminRepo.save(adminInfo);
		return savedAdmin;
	}

	public Admin getAdminById(Long id) {
		// TODO Auto-generated method stub
		Admin admin=adminRepo.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Admin does not exist for ID: "+id));
		return admin;
	}


	

}
