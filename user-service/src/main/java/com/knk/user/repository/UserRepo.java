package com.knk.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knk.user.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findByUserId(Long id);
	
	Optional<User> findByUserName(String userName);

}
