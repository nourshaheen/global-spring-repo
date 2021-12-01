package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	public Employee findById(Long id) {

		return employeeRepo.findById(id).orElseThrow();
	}

	public List<Employee> filter(String name) {

		return employeeRepo.filterNative(name);
	}

	public Employee insert(Employee emp) {

		return employeeRepo.save(emp);
	}

	public Employee update(Employee emp) {

		Employee current = employeeRepo.findById(emp.getId()).orElseThrow();

		current.setName(emp.getName());
		current.setSalary(emp.getSalary());
		current.setDepartment(emp.getDepartment());

		return employeeRepo.save(current);
	}

	public List<Employee> findByDepartmentId(Long deptId) {

		return employeeRepo.findByDepartment(deptId);
	}
	
	public List<Employee> findAll() {

		return employeeRepo.findAll();
	}

}
