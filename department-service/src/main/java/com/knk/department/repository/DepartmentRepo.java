package com.knk.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knk.department.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

	Department findByDepartmentId(Long id);

}
