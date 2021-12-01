package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.Employee;
import com.global.hr.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/{id}")
	public Employee findById(@PathVariable Long id) {

		return employeeService.findById(id);
	}

	@GetMapping("/filter")
	public List<Employee> findByName(@RequestParam String name) {

		return employeeService.filter(name);
	}

	@PostMapping()
	public Long insert(@RequestBody Employee emp) {
		Employee inserted = employeeService.insert(emp);
		return inserted.getId();
	}

	@PutMapping
	public Employee update(@RequestBody Employee emp) {

		return employeeService.update(emp);
	}
	
	@GetMapping("/department/{deptId}")
	public List<Employee> findByDepartmentId(@PathVariable Long deptId) {

		return employeeService.findByDepartmentId(deptId);
	}

}
