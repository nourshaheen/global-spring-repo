package com.global.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.hr.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long>{
	
	@Modifying
	@Transactional
	@Query(value = "delete from Department dept where dept.name = :deptName")
	int deleteByName(String deptName);
	
	
	@Modifying
	@Transactional
	@Query(value = "update Department dept set name = :deptName where dept.name = :deptName")
	int updateByName(String deptName);
	
}
