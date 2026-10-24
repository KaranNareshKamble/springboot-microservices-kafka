package com.knk.user.service;

import java.time.Instant;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.knk.user.dto.UserCreatedEvent;
import com.knk.user.entity.User;
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

	public User addUser(User user) {
		// TODO Auto-generated method stub
		log.info("################################# addUser");

		User savedUser= userRepo.save(user);
		
		UserCreatedEvent userCreatedEvent= new UserCreatedEvent(
				savedUser.getUserId(), savedUser.getUserName(),
				savedUser.getDepartmentId(), Instant.now()
				);
log.info("#######################################before kafka send");
		
kafkaTemplate.send(USER_EVENT_TOPIC, userCreatedEvent).whenComplete((result, ex)->{
	if(ex!=null) {
		log.info("#####Error in Kafka send#####");
	}else {
		log.info("Kafka send successfully: topic:{}, partition:{}, offset:{} ",
				result.getRecordMetadata().topic(), result.getRecordMetadata().partition(),
				result.getRecordMetadata().offset());
	}
});


log.info("########################################after kafka send");
		return savedUser;
	}

	public User getByID(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findByUserId(id);
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
	

}
