package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	public long count() {

		return employeeRepo.count();
	}

	public Employee findById(Long id) {

		return employeeRepo.findById(id).get();
	}

	public Iterable<Employee> findAll() {

		return employeeRepo.findAll();
	}

	public Employee insert(Employee emp) {

		return employeeRepo.save(emp);
	}

	public Employee update(Employee emp) {

		return employeeRepo.save(emp);
	}

	public List<Employee> findByName(String name) {

		return employeeRepo.findByName(name);

	}

	public List<Employee> findByEmployeeId(String name) {

		return employeeRepo.findByEmployeeId(name);
	}

	public List<Employee> findByNameAndSalary(@Param("empName") String name, @Param("empSalary") Double salary) {

		return employeeRepo.findByNameAndSalary(name, salary);
	}
	
	
	public void deleteById(Long id) {

		employeeRepo.deleteById(id);
	}
	
	public int updateSalary (Double salary, Long id) {
		
		return employeeRepo.updateSalary(salary, id);
	}

}
