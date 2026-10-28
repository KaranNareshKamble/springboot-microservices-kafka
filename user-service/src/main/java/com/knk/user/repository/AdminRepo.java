package com.knk.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knk.user.entity.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

	
}
