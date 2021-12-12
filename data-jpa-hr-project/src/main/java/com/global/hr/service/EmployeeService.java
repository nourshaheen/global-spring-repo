package com.global.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Department;
import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private DepartmentService departmentService;

	public Employee findById(Long id) {

		return employeeRepo.findById(id).orElseThrow();
	}
	
	public List<Employee> findByEmpAndDept(String empName, String deptName){
		
		return employeeRepo.findByFirstNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	public Long countByEmpAndDept(String empName, String deptName) {

		return employeeRepo.countByFirstNameContainingAndDepartmentNameContaining(empName, deptName);
	}

	
	public Long deleteByEmpAndDept(String empName, String deptName) {

		return employeeRepo.deleteByFirstNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	
	public Page<Employee> filter(String name ,int pageNum, int pageSize, String sortCol, Boolean isAsc) {

		if (name.isEmpty() || name.isBlank() || name == null) {
			name = null;
		}
		
		// Sort object with List of Order objects.
//		List<Order> orders = new ArrayList<Order>();
//
//		Order order1 = new Order(isAsc ? Direction.ASC : Direction.DESC, sortCol);
//		orders.add(order1);
//
//		Order order2 = new Order(Sort.Direction.ASC, "title");
//		orders.add(order2);
		
		Pageable page = PageRequest.of(pageNum, pageSize, Sort.by(isAsc ? Direction.ASC : Direction.DESC  ,sortCol));
		
		return employeeRepo.filter(name, page);
	}

	public Employee insert(Employee emp) {
		
		if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			
			Department dept = departmentService.findById(emp.getDepartment().getId());
			dept.setName(emp.getDepartment().getName());
			
			emp.setDepartment(dept);
		}

		return employeeRepo.save(emp);
	}

	public Employee update(Employee emp) {

		Employee current = employeeRepo.findById(emp.getId()).orElseThrow();

		current.setFirstName(emp.getFirstName());
		current.setLastName(emp.getLastName());
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
	
	public List<Employee> findBySalary (Double salary, String name){
		
		return employeeRepo.findBySalary(salary, name);
	}
	
	public HRStatisticProjection getHRStatistic () {
		
		return employeeRepo.getHRStatistic();
				
	}

}
