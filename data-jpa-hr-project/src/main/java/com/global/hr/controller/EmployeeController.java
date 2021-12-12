package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;
import com.global.hr.entity.EmployeeReponse;
import com.global.hr.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/{id}")
	public EmployeeReponse findById(@PathVariable Long id) {
		
		Employee emp = employeeService.findById(id);
		
		EmployeeReponse res= new EmployeeReponse ();
		res.setId(emp.getId());
		res.setName(emp.getFirstName());
		res.setDepartment(emp.getDepartment());
		res.setUser(emp.getUser());
		

		return res;
	}
	
	
	@GetMapping("/emp-dept")
	public List<Employee> findByEmpAndDept(@RequestParam String empName,@RequestParam String deptName) {

		return employeeService.findByEmpAndDept(empName, deptName);
	}
	
	
	@GetMapping("/count-emp-dept")
	public ResponseEntity<Long> countByEmpAndDept(@RequestParam String empName,@RequestParam String deptName) {

		return ResponseEntity.ok(employeeService.countByEmpAndDept(empName, deptName));
	}
	
	@DeleteMapping("/emp-dept")
	public ResponseEntity<Long> deleteByEmpAndDept(@RequestParam String empName,@RequestParam String deptName) {

		return ResponseEntity.ok(employeeService.deleteByEmpAndDept(empName, deptName));
	}

	@GetMapping("/filter")
	public ResponseEntity<?> findByName(@RequestParam String name ,@RequestParam int pageNum, @RequestParam int pageSize
			, @RequestParam String sortCol , @RequestParam Boolean isAsc) {

		return ResponseEntity.ok(employeeService.filter(name, pageNum, pageSize, sortCol, isAsc));
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
	
	@GetMapping("/salary")
	public ResponseEntity<?> findBySalary(@RequestParam Double salary, @RequestParam String name) {

		return  ResponseEntity.ok(employeeService.findBySalary(salary , name)) ;
	}
	
	@GetMapping("statistic")
	public ResponseEntity<?> getHRStatistic() {

		return ResponseEntity.ok(employeeService.getHRStatistic());

	}

}
