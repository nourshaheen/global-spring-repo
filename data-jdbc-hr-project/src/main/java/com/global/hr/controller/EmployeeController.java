package com.global.hr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;
import com.global.hr.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;



//	@GetMapping("/count")
	@RequestMapping(method = RequestMethod.GET ,path = "/count")
	public long countEmp() {

		return employeeService.count();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id ,@RequestHeader("accept-language") String acceptLanguage) {

		log.info(" Accept Language is " + acceptLanguage);
		
		return ResponseEntity.ok(employeeService.findById(id));
	}

	@GetMapping("")
	public Iterable<Employee> findAll() {

		return employeeService.findAll();
	}

	@PostMapping("")
	public Employee addEmp(@RequestBody Employee emp) {

		return employeeService.insert(emp);
	}

	@PutMapping("")
	public Employee updateEmp(@RequestBody Employee emp) {

		return employeeService.update(emp);
	}

	@GetMapping("/filter")
	public List<Employee> filter(@RequestParam String name, @RequestParam Double salary) {

		return employeeService.findByNameAndSalary(name, salary);
	}

	@DeleteMapping("/{empId}")
	public void deleteEmp(@PathVariable(name = "empId") Long id) {

		employeeService.deleteById(id);
	}
	
	@PutMapping("/salary")
	public int updateSalary (@RequestParam Double salary, @RequestParam Long id) {
			
			return employeeService.updateSalary(salary, id);
	}
	
	
	public void testJaskson() throws JsonMappingException, JsonProcessingException {
	
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonString = "{\r\n"
				+ "        \"employeeId\": 20,\r\n"
				+ "        \"name\": \"Mohamed\",\r\n"
				+ "        \"salary\": 5221.0,\r\n"
				+ "        \"isCreate\": null\r\n"
				+ "    }";
		
		//Object to JSON Conversion
		Employee empObject = mapper.readValue(jsonString, Employee.class);

		//Object to JSON Conversion		
		jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(empObject);
	}

}
