package com.global.hr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	
	
	List<Employee> findBySalary (Double salary, String name);	
	
	List<Employee> findByDepartment (@Param("deptId") Long deptId);
	
	List<Employee> findByFirstNameContainingAndDepartmentNameContaining(String empName, String deptName);	
	
	Long countByFirstNameContainingAndDepartmentNameContaining(String empName, String deptName);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional()
	Long deleteByFirstNameContainingAndDepartmentNameContaining(String empName, String deptName);
	
	
	// this is the JPQL 
	@Query(value = "select emp from #{#entityName} emp where (:empName is null or emp.firstName like :empName)")
	Page<Employee> filter(@Param("empName") String name, Pageable pageable);
	
	// this is the sql native 
	@Query(value = "select * from hr_employees emp where emp.first_Name like :empName", nativeQuery = true)
	List<Employee> filterNative(@Param("empName") String name);
	
	List<Employee> findByDepartmentId (Long deptId);
	
	@Query(value = "select (select count(*) from hr_departments) deptCount,"
			+ " (select count(*) from hr_employees) empCount,"
			+ " (select count(*) from sec_users ) userCount"
			, nativeQuery = true)
	HRStatisticProjection getHRStatistic ();
	
	
//	@Query(value = "select emp from Employee emp join emp.department dept where dept.id = :deptId")
//	List<Employee> findByDepartment (Long deptId);

}
